package com.caltech.natalassistsplus;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

public class ForumFragment extends Fragment {

    RecyclerView forumRecyclerView;
    RecyclerView.LayoutManager forumLayoutManager;
    ForumRecyclerViewAdapter forumRecyclerViewAdapter;
    SwipeRefreshLayout swipeRefreshLayout;
    ArrayList<ForumPost> forumPosts = new ArrayList<>();
    FirebaseFirestore db = FirebaseFirestore.getInstance();

    public ForumFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_forum, container, false);
        forumRecyclerView = view.findViewById(R.id.forumRV);
        swipeRefreshLayout = view.findViewById(R.id.swipeRefreshForum);
        forumLayoutManager = new LinearLayoutManager(getContext());

        forumPosts.add(new ForumPost("Josh", R.drawable.user1, "22 hours ago",
                "Can an adult eat baby food to survive? 😅😅", R.drawable.baby_food));
        forumPosts.add(new ForumPost("Mark", R.drawable.user2, "30 minutes ago",
                "Look how this cute new born baby is 😍😍😍", R.drawable.cute_baby));
        forumPosts.add(new ForumPost("Nicholas", R.drawable.user3, "an hour ago",
                "I have a new born baby coming, this is actually my first baby," +
                        "and do you guys have any suggestion on how to name my baby?" +
                        "Any cute or nice name will do. 😁", 0));
        forumPosts.add(new ForumPost("Johnny", R.drawable.user4, "3 hours ago",
                "Does any experienced mum here knows what a pregnancy test look like?", 0));
        forumPosts.add(new ForumPost("Bob", R.drawable.user5, "10 hours ago",
                "My baby haven’t pooped for like a week, is that normal? Do " +
                        "I need to bring him to the doctor?💩💩💩", 0));
        forumPosts.add(new ForumPost("Noah", R.drawable.user6, "3 days ago",
                "My wive is having a back pain very frequently lately, does" +
                        "anyone know what is going on?", R.drawable.back_pain));
        forumPosts.add(new ForumPost("Nicky", R.drawable.user7, "5 days ago",
                "Any suggestion on how to cook a nice meal for my wife?", R.drawable.dinner));
        forumPosts.add(new ForumPost("Tyler", R.drawable.user8, "a week ago",
                "Is it normal to feel a baby kick often? Does it mean that I'm going" +
                        "into labour soon?😟😟😟😟", R.drawable.babykick));
        forumPosts.add(new ForumPost("Johnathan", R.drawable.user9, "4 days ago",
                "How hot should I make the formula milk for my baby?", 0));
        forumPosts.add(new ForumPost("Jimmy", R.drawable.user10, "2 days ago",
                "Is it normal for my wife to have bad mood often during pregnancy?", 0));

        forumRecyclerViewAdapter = new ForumRecyclerViewAdapter(forumPosts);
        forumRecyclerView.setHasFixedSize(true);
        forumRecyclerView.setLayoutManager(forumLayoutManager);
        forumRecyclerView.setAdapter(forumRecyclerViewAdapter);
        fetchPostData();

        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                fetchPostData();
            }
        });

        return view;
    }

    private void fetchPostData(){
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("h:mm a", Locale.getDefault());

        db.collection("forumPosts")
                .get()
                .addOnCompleteListener(task -> {
                    if(task.isSuccessful()){
                        boolean isExists;
                        for (QueryDocumentSnapshot document : task.getResult()) {
                            isExists = false;
                            for(ForumPost forumPost:forumPosts){
                                if(forumPost.getPostDesc().equals(document.getString("PostDesc")) &&
                                        forumPost.getUsername().equals(document.getString("Username"))){
                                    isExists = true;
                                    break;
                                }
                            }

                            if(!isExists){
                                forumPosts.add(new ForumPost(document.getString("Username"), R.drawable.ic_baseline_supervised_user_circle_24,
                                        simpleDateFormat.format(new Date()), document.getString("PostDesc"), 0));
                            }
                        }
                        forumRecyclerViewAdapter.notifyDataSetChanged();
                        swipeRefreshLayout.setRefreshing(false);
                    }
                });
    }

}