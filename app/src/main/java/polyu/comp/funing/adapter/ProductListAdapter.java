package polyu.comp.funing.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

import polyu.comp.funing.R;
import polyu.comp.funing.domain.Product;

/**
 * Created by liushanchen on 16/3/19.
 */
public class ProductListAdapter extends BaseAdapter {
    List<Product> myList=null;
    Context context;
    LayoutInflater layoutInflater;

    public ProductListAdapter(Context context) {
        this.context = context;
        this.layoutInflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        if(myList!=null){
            return myList.size();
        }else{
            return 0;
        }
    }

    @Override
    public Product getItem(int position) {
        return myList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Product item = getItem(position);
        View view = convertView;
        if (view == null) {
            view = layoutInflater.inflate(R.layout.product_list_item, parent, false);
        }
        ImageView pItemImg=(ImageView)view.findViewById(R.id.p_item_img);
        TextView pName=(TextView)view.findViewById(R.id.textViewl);
        TextView pPrice=(TextView)view.findViewById(R.id.textView);
        Picasso.with(context).load(item.getP_image_url()).resize(200, 200).centerCrop().into(pItemImg);
        pName.setText(item.getP_name());
        pPrice.setText(item.getP_price()+"");
        
        return view;
    }

    public List<Product> getMyList() {
        return myList;
    }

    public void setMyList(List<Product> myList) {
        this.myList = myList;
    }
}
