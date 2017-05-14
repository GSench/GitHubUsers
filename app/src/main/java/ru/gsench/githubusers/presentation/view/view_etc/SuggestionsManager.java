package ru.gsench.githubusers.presentation.view.view_etc;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Parcel;

import com.arlib.floatingsearchview.FloatingSearchView;
import com.arlib.floatingsearchview.suggestions.model.SearchSuggestion;

import java.util.ArrayList;

/**
 * Created by grish on 12.05.2017.
 */

public class SuggestionsManager {

    private static final String SUGGESTIONS = "suggestions";
    private static final String COUNT = "count";
    private static final String SUGGESTION = "s";

    private FloatingSearchView searchView;

    private SharedPreferences preferences;
    private ArrayList<String> vocabulary;

    public SuggestionsManager(FloatingSearchView searchView){
        this.searchView=searchView;
    }

    public void init(){
        preferences = searchView.getContext().getSharedPreferences(SUGGESTIONS, Context.MODE_PRIVATE);
        vocabulary = new ArrayList<>();
        int c = preferences.getInt(COUNT, 0);
        for (int i=0; i<c; i++) vocabulary.add(preferences.getString(SUGGESTION+i, ""));
    }

    public void saveSuggestion(String query){
        for(String sug: vocabulary) if(sug.toLowerCase().equals(query.toLowerCase())) return;
        preferences
                .edit()
                .putString(SUGGESTION+vocabulary.size(), query)
                .putInt(COUNT, vocabulary.size()+1)
                .commit();
        vocabulary.add(query);
    }

    public void suggest(String query){
        ArrayList<SearchSuggestion> suggestions = new ArrayList<>();
        for(int i=0; i<vocabulary.size(); i++){
            if(vocabulary.get(i).toLowerCase().startsWith(query.toLowerCase())) {
                final int finalI = i;
                suggestions.add(new SearchSuggestion() {
                    @Override
                    public String getBody() {
                        return vocabulary.get(finalI);
                    }

                    @Override
                    public int describeContents() {
                        return 0;
                    }

                    @Override
                    public void writeToParcel(Parcel parcel, int i) {
                        parcel.writeString(vocabulary.get(finalI));
                    }
                });
            }
        }
        searchView.swapSuggestions(suggestions);
    }

}
