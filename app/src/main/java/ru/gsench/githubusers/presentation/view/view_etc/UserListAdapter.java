package ru.gsench.githubusers.presentation.view.view_etc;

import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

import ru.gsench.githubusers.R;
import ru.gsench.githubusers.domain.github_repo.GitHubUserShort;
import ru.gsench.githubusers.presentation.view.aview.UserListAView;

import static ru.gsench.githubusers.R.layout.user;

/**
 * Created by grish on 10.05.2017.
 */

public class UserListAdapter extends RecyclerView.Adapter<UserListAdapter.ViewHolder> {

    private UserListAView aView;

    private ProgressBar loading;
    private boolean showLoading = false;

    public UserListAdapter(UserListAView aView) {
        this.aView=aView;
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int i) {

        if(viewHolder instanceof HeaderViewHolder){

            HeaderViewHolder headerViewHolder = (HeaderViewHolder) viewHolder;

        } else if(viewHolder instanceof FooterViewHolder){

            FooterViewHolder footerViewHolder = (FooterViewHolder) viewHolder;

        } else if(viewHolder instanceof NormalViewHolder){

            NormalViewHolder normalViewHolder = (NormalViewHolder) viewHolder;

            final GitHubUserShort user = aView.getUserAt(i-1);
            normalViewHolder.name.setText(user.getLogin());
            Glide
                    .with(aView.context)
                    .load(user.getAvatar().toString())
                    .crossFade()
                    .diskCacheStrategy(DiskCacheStrategy.NONE)
                    .into(normalViewHolder.avatar);
            normalViewHolder.addToFavor.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    aView.addToFavor(user);
                }
            });
            normalViewHolder.main.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    aView.onUserClicked(user);
                }
            });

        }
    }

    @Override
    public int getItemCount() {
        return aView.getUserCount()+2;
    }

    public void notifyItemAdded(int offset, int count){
        notifyItemRangeInserted(offset+1, count);
    }

    public void clearList(){
        notifyItemRangeRemoved(1, aView.getUserCount());
    }

    public void showLoading(){
        showLoading=true;
        if(loading!=null) loading.setVisibility(View.VISIBLE);
    }

    public void hideLoading(){
        showLoading=false;
        if(loading!=null) loading.setVisibility(View.INVISIBLE);
    }

    private static final int FOOTER_VIEW = 2;
    private static final int HEADER_VIEW = 1;

    class ViewHolder extends RecyclerView.ViewHolder {
        public ViewHolder(View itemView) {
            super(itemView);
        }
    }

    class HeaderViewHolder extends ViewHolder {
        HeaderViewHolder(View itemView) {
            super(itemView);
        }
    }

    class FooterViewHolder extends ViewHolder {
        FooterViewHolder(View itemView) {
            super(itemView);
            loading = (ProgressBar) itemView.findViewById(R.id.progress_bar);
            if(showLoading) loading.setVisibility(View.VISIBLE);
            else loading.setVisibility(View.INVISIBLE);
        }
    }

    class NormalViewHolder extends ViewHolder {
        private CardView main;
        private TextView name;
        private ImageView avatar;
        private Button addToFavor;

        NormalViewHolder(View itemView) {
            super(itemView);
            main = (CardView) itemView.findViewById(R.id.user_card);
            name = (TextView) itemView.findViewById(R.id.user_name);
            avatar = (ImageView) itemView.findViewById(R.id.user_avatar);
            addToFavor = (Button) itemView.findViewById(R.id.add_to_favorites);
        }
    }

    @Override
    public UserListAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v;
        switch (viewType){
            case FOOTER_VIEW:
                v = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_view_footer, parent, false);
                FooterViewHolder footer = new FooterViewHolder(v);
                return footer;
            case HEADER_VIEW:
                v = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_view_header, parent, false);
                HeaderViewHolder header = new HeaderViewHolder(v);
                return header;
        }
        v = LayoutInflater.from(parent.getContext()).inflate(user, parent, false);
        NormalViewHolder norm = new NormalViewHolder(v);
        return norm;
    }

    @Override
    public int getItemViewType(int position) {
        int ret;
        if(position==0) ret=HEADER_VIEW;
        else if(position==1&&aView.getUserCount()==0) ret= FOOTER_VIEW;
        else if(position==aView.getUserCount()+1) ret= FOOTER_VIEW;
        else ret = super.getItemViewType(position);
        return ret;
    }

// So you're done with adding a footer and its action on onClick.
// Now set the default ViewHolder for NormalViewHolder
}