package ru.gsench.githubusers.presentation.utils;

import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

/**
 * Created by grish on 13.05.2017.
 */

public abstract class HeaderFooterAdapter<HVH extends HeaderViewHolder, NVH extends NormalViewHolder, FVH extends FooterViewHolder> extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, int i) {

        if(viewHolder instanceof HeaderViewHolder){
            HVH headerViewHolder = (HVH) viewHolder;
            onBindHeaderViewHolder(headerViewHolder);
        } else if(viewHolder instanceof FooterViewHolder){
            FVH footerViewHolder = (FVH) viewHolder;
            onBindFooterViewHolder(footerViewHolder);
        } else if(viewHolder instanceof NormalViewHolder){
            NVH normalViewHolder = (NVH) viewHolder;
            onBindNormalViewHolder(normalViewHolder, i-1);
        }
    }

    public abstract void onBindHeaderViewHolder(HVH viewHolder);
    public abstract void onBindNormalViewHolder(NVH viewHolder, int i);
    public abstract void onBindFooterViewHolder(FVH viewHolder);

    public abstract HeaderViewHolder onCreateHeaderViewHolder(ViewGroup parent);
    public abstract NormalViewHolder onCreateNormalViewHolder(ViewGroup parent);
    public abstract FooterViewHolder onCreateFooterViewHolder(ViewGroup parent);


    @Override
    public final int getItemCount() {
        return getNormalItemsCount()+2;
    }

    public abstract int getNormalItemsCount();

    public void notifyItemsAdded(int offset, int count){
        notifyItemRangeInserted(offset+1, count);
    }

    public void notifyNormalItemChanged(int i){
        notifyItemChanged(i+1);
    }

    public void clearList(){
        notifyItemRangeRemoved(1, getNormalItemsCount());
    }

    private static final int FOOTER_VIEW = 2;
    private static final int HEADER_VIEW = 1;

    @Override
    public final RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        switch (viewType){
            case FOOTER_VIEW:
                return onCreateFooterViewHolder(parent);
            case HEADER_VIEW:
                return onCreateHeaderViewHolder(parent);
            default:
                return onCreateNormalViewHolder(parent);
        }
    }

    @Override
    public final int getItemViewType(int position) {
        int ret;
        if(position==0) ret=HEADER_VIEW;
        else if(position==1&&getNormalItemsCount()==0) ret= FOOTER_VIEW;
        else if(position==getNormalItemsCount()+1) ret= FOOTER_VIEW;
        else ret = super.getItemViewType(position);
        return ret;
    }

}