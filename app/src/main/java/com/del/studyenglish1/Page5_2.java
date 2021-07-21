package com.del.studyenglish1;


import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;


public class Page5_2 extends Fragment {
    private static final String ARG_TYPE = "argType";
    private static final String ARG_LEVEL_NAME = "argLevelName";
    private static final String ARG_LEVEL = "argLevel";

    private String type;
    private String level_name;
    private String level;

    private Page5 page5;
    private Page5_1 page5_1;
    private Page_5_3_Grammar page_5_3_grammar;
    private TextView textViewType;
    private TextView changeLevel;
    private TextView changeType;
    private SQLiteDatabase newDb;
    private ListView recyclerView;
    private ListView listView;
    //private TopicAdapter adapter;
    //private SimpleCursorAdapter mAdapter;
    private MyTopicAdapter myTopicAdapter;
    private RecyclerView.LayoutManager mLayoutManager;

    //private ArrayList<Topic> mTopics = new ArrayList<>();



    public Page5_2() {
        // Required empty public constructor
    }

    public static Page5_2 newInstance(String type, String level, String level_name) {
        Page5_2 fragment = new Page5_2();
        Bundle args = new Bundle();
        args.putString(ARG_TYPE, type);
        args.putString(ARG_LEVEL_NAME, level_name);
        args.putString(ARG_LEVEL, level);
        fragment.setArguments(args);
        return fragment;
    }



    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_page5_2, container, false);
        textViewType = v.findViewById(R.id.text_view_type);
        //recyclerView = v.findViewById(R.id.recycler_topic);
        listView = (ListView) v.findViewById(R.id.recycler_topic);

        QuizDbHelper dbHelper = new QuizDbHelper(getActivity());
        newDb = dbHelper.getReadableDatabase();

        loadTopics();





        if (getArguments() != null) {
            type = getArguments().getString(ARG_TYPE);
            level_name = getArguments().getString(ARG_LEVEL_NAME);
        }
        textViewType.setText(level_name + ": " + type);

        return v;
    }





    //displayListView();


        //listView.setAdapter();
/*
        recyclerView = v.findViewById(R.id.recycler_topic);
        recyclerView.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(mLayoutManager);
        adapter = new TopicAdapter(getActivity(), getTopics());

        recyclerView.setAdapter(adapter);

        adapter.setOnItemClickListener(new TopicAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {

                String topicName = getTopicName(position);
                page_5_3_grammar = Page_5_3_Grammar.newInstance(topicName, type, level_name);
                FragmentTransaction fragmentTransaction = getActivity().getSupportFragmentManager().beginTransaction();
                fragmentTransaction.replace(R.id.nav_host_fragment, page_5_3_grammar);
                fragmentTransaction.commit();
                //Toast.makeText(getActivity(), "PEraLTA, yOu'RE a GENIus", Toast.LENGTH_SHORT).show();
            }
        });*/


/*
    private void displayListView() {
        Cursor c = getTopics();
        String[] columns = new String[] {
                QuizContract.TopicsTable.COLUMN_NAME
        };

        int[] to = new int[] {
                R.id.button_topic_item
        };

        mAdapter = new SimpleCursorAdapter(
                getContext(),
                R.layout.topic_item,
                c,
                columns,
                to,
                0
        );

        listView = (ListView) getView().findViewById(R.id.recycler_topic);
        listView.setAdapter(mAdapter);


        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> listView, View view, int position, long id) {
                Cursor c = (Cursor) listView.getItemAtPosition(position);

                String topic = c.getString(c.getColumnIndex("name"));
                Toast.makeText(getContext(), topic, Toast.LENGTH_SHORT).show();
            }
        });

        mAdapter.setFilterQueryProvider(new FilterQueryProvider() {
            @Override
            public Cursor runQuery(CharSequence constraint) {
                return getTopics();
            }
        });
    }*/

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        changeLevel = (TextView) view.findViewById(R.id.text_view_change_level);
        changeType = (TextView) view.findViewById(R.id.text_view_change_type);
        listView = (ListView) view.findViewById(R.id.recycler_topic);


        changeLevel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                page5 = new Page5();
                FragmentTransaction fragmentTransaction = getActivity().getSupportFragmentManager().beginTransaction();
                fragmentTransaction.replace(R.id.nav_host_fragment, page5);
                fragmentTransaction.commit();
            }
        });

        changeType.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                level_name = getArguments().getString(ARG_LEVEL_NAME);
                level = getArguments().getString(ARG_LEVEL);
                page5_1 = page5_1.newInstance(level, level_name);
                FragmentTransaction fragmentTransaction = getActivity().getSupportFragmentManager().beginTransaction();
                fragmentTransaction.replace(R.id.nav_host_fragment, page5_1);
                fragmentTransaction.commit();
            }
        });
    }
    /**
     * @return Cursor finds all topics filtered by chosen type and level
     */

/*
    private Cursor getTopics() {
        type = getArguments().getString(ARG_TYPE);
        level_name = getArguments().getString(ARG_LEVEL_NAME);

        String selection = QuizContract.TopicsTable.COLUMN_TYPE + " = ? " +
                " AND " + QuizContract.TopicsTable.COLUMN_DIFFICULTY + " = ?";
        String[] selectionArgs = new String[]{type, level_name};

        return newDb.query(
                QuizContract.TopicsTable.TABLE_NAME,
                //new String[]{QuizContract.TopicsTable.COLUMN_NAME},
                null,
                selection,
                selectionArgs,
                null,
                null,
                null
                );
    }

    private String getTopicName(int id) {
        type = getArguments().getString(ARG_TYPE);
        level_name = getArguments().getString(ARG_LEVEL_NAME);

        String selection = QuizContract.TopicsTable._ID + " = ? ";
        String[] selectionArgs = new String[]{String.valueOf(id)};

        Cursor c =  newDb.query(
                QuizContract.TopicsTable.TABLE_NAME,
                new String[]{QuizContract.TopicsTable.COLUMN_NAME},
                //null,
                selection,
                selectionArgs,
                null,
                null,
                null
        );
        String selectedType = c.getString(c.getPosition());
        return selectedType;
    }
*/

    private void loadTopics() {
        type = getArguments().getString(ARG_TYPE);
        level_name = getArguments().getString(ARG_LEVEL_NAME);
        QuizDbHelper dbHelper = QuizDbHelper.getInstance(getContext());
        ArrayList<Topic> topicList = dbHelper.getTopics(type, level_name);

        MyTopicAdapter myTopicAdapter = new MyTopicAdapter(getContext(), topicList);
        listView.setAdapter(myTopicAdapter);

        Button topicButton = (Button) listView.findViewById(R.id.button_topic_item);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

        //listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {

                    String topicSelected = listView.getItemAtPosition(position).toString();
                    Page_5_3_Grammar fragment = Page_5_3_Grammar.newInstance(topicSelected, type, level_name);
                    FragmentTransaction fragmentTransaction = getActivity().getSupportFragmentManager().beginTransaction();
                    fragmentTransaction.replace(R.id.nav_host_fragment, fragment);
                    fragmentTransaction.commit();
            }
        });

    }
        /*
        ArrayAdapter<Topic> adapterTopic = new ArrayAdapter<>(
                getContext(),
                R.layout.topic_item,
                topicList);
        listView.setAdapter(adapterTopic);
    }*/

    private void loadGrammarPage(String mTopic, String mType, String mLevel_name) {
        page_5_3_grammar = page_5_3_grammar.newInstance(mTopic, mType, mLevel_name);
        FragmentTransaction fragmentTransaction = getActivity().getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.nav_host_fragment, page_5_3_grammar);
        fragmentTransaction.commit();

    }
}