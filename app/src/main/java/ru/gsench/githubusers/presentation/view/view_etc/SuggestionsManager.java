package ru.gsench.githubusers.presentation.view.view_etc;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Parcel;

import com.arlib.floatingsearchview.FloatingSearchView;
import com.arlib.floatingsearchview.suggestions.model.SearchSuggestion;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by grish on 12.05.2017.
 */

public class SuggestionsManager {

    private static final String SUGGESTIONS = "suggestions";

    private FloatingSearchView searchView;

    private SharedPreferences preferences;
    private ArrayList<String> vocabulary;

    public SuggestionsManager(FloatingSearchView searchView){
        this.searchView=searchView;
    }

    public void init(){
        preferences = searchView.getContext().getSharedPreferences(SUGGESTIONS, Context.MODE_PRIVATE);
        vocabulary = new ArrayList<>(Arrays.asList(getSavedStringArray(SUGGESTIONS, new String[0])));
    }

    public void saveSuggestion(String query){
        for(int i=0; i<vocabulary.size(); i++)
            if(vocabulary.get(i).toLowerCase().equals(query.toLowerCase())){
                vocabulary.remove(i);
                break;
        }
        vocabulary.add(query);
        preferences
                .edit()
                .putStringSet(SUGGESTIONS, new HashSet<>(vocabulary))
                .commit();
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

    public String[] getSavedStringArray(String title, String[] def) {
        Set<String> defaultArr;
        if(def==null) defaultArr = null;
        else defaultArr = new HashSet<>(Arrays.asList(def));
        Set<String> stringSet = preferences.getStringSet(title, defaultArr);
        return stringSet!=null ? stringSet.toArray(new String[stringSet.size()]) : null;
    }

}
