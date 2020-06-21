package com.example.retrofitExample2;

import android.os.Bundle;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    private TextView Result;
    private JsonPlaceHolderAPI jsonPlaceHolderApi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Result = findViewById(R.id.ResultId);
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://jsonplaceholder.typicode.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        jsonPlaceHolderApi = retrofit.create(JsonPlaceHolderAPI.class);
        //getPosts();
        //getComments();
        createPosts();
    }

    private void createPosts() {
        //Post post = new Post(23, "New Title", "New Text");

        Map<String, String> fields = new HashMap<>();
        fields.put("userId","25");
        fields.put("title","New Title Sir");
        fields.put("body", "New Body bro");

        /*Call<Post> call = jsonPlaceHolderApi.createPost(25,"New Title","New Body");*/
        Call<Post> call = jsonPlaceHolderApi.createPost(fields);

        call.enqueue(new Callback<Post>() {
            @Override
            public void onResponse(Call<Post> call, Response<Post> response) {
                if (!response.isSuccessful()){
                    Result.setText("Code: " + response.code());
                    return;
                }

                Post postResponse = response.body();
                String content = "";
                assert postResponse != null;
                content += "Code: " + response.code() + "\n";
                content += "ID: " + postResponse.getId() + "\n";
                content += "User ID: " + postResponse.getUserId() + "\n";
                content += "Title: " + postResponse.getTitle() + "\n";
                content += "Text: " + postResponse.getText() + "\n\n";
                Result.setText(content);
            }

            @Override
            public void onFailure(Call<Post> call, Throwable t) {
                Result.setText(t.getMessage());
            }
        });
    }

    private void getComments() {
        Call<List<Comments>> call = jsonPlaceHolderApi.getComments(3);
        call.enqueue(new Callback<List<Comments>>() {
            @Override
            public void onResponse(Call<List<Comments>> call, Response<List<Comments>> response) {
                if (!response.isSuccessful()) {
                    Result.setText("Code: " + response.code());
                    return;
                }
                List<Comments> comments = response.body();
                assert comments != null;
                for (Comments comment : comments) {
                    String content = "";
                    content += "ID: " + comment.getId() + "\n";
                    content += "User ID: " + comment.getPostId() + "\n";
                    content += "Name: " + comment.getName() + "\n";
                    content += "Email: " + comment.getEmail() + "\n";
                    content += "Text: " + comment.getText() + "\n\n";
                    Result.append(content);
                }
            }
            @Override
            public void onFailure(Call<List<Comments>> call, Throwable t) {
                Result.setText(t.getMessage());
            }
        });
    }

    private void getPosts() {
        Map<String,String> parameters = new HashMap<>();
        parameters.put("userId","1");
        parameters.put("_sort","id");
        parameters.put("_order","desc");

        Call<List<Post>> call = jsonPlaceHolderApi.getPosts(parameters);
        /*Call<List<Post>> call = jsonPlaceHolderApi.getPosts("id","desc", 2,3,6);*/            //id using varargs
        //Call<List<Post>> call = jsonPlaceHolderApi.getPosts("id","desc", new Integer[]{2,3,6});           //id using Integer[] in JsonPlaceHolder.java file
        call.enqueue(new Callback<List<Post>>() {
            @Override
            public void onResponse(Call<List<Post>> call, Response<List<Post>> response) {
                if (!response.isSuccessful()) {
                    Result.setText("Code: " + response.code());
                    return;
                }
                List<Post> posts = response.body();
                assert posts != null;
                for (Post post : posts) {
                    String content = "";
                    content += "ID: " + post.getId() + "\n";
                    content += "User ID: " + post.getUserId() + "\n";
                    content += "Title: " + post.getTitle() + "\n";
                    content += "Text: " + post.getText() + "\n\n";
                    Result.append(content);
                }
            }
            @Override
            public void onFailure(Call<List<Post>> call, Throwable t) {
                Result.setText(t.getMessage());
            }
        });
    }
}
