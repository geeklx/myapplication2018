package com.example.p006_recycleview_alluses.activitys;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.librecycleview.ItemViewBinder;
import com.example.librecycleview.Items;
import com.example.librecycleview.MultiTypeAdapter;
import com.example.p006_recycleview_alluses.R;
import com.example.p006_recycleview_alluses.apis.TextItem;

import static android.os.SystemClock.currentThreadTimeMillis;
import static java.lang.String.valueOf;


public class MoreApisPlayground extends AppCompatActivity {

  private static final String TERMINAL_DEFAULT_TEXT = "ObservableTextItemViewBinder: ";

  private TextView terminal;
  private RecyclerView recyclerView;

  private MultiTypeAdapter adapter;
  private Items items;


  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_more_apis_playground);
    recyclerView = findViewById(R.id.list);
    terminal = findViewById(R.id.terminal);
    terminal.setText(TERMINAL_DEFAULT_TEXT);

    items = new Items();
    adapter = new MultiTypeAdapter();

    adapter.register(TextItem.class, new ObservableTextItemViewBinder());
    recyclerView.setAdapter(adapter);

    for (int i = 0; i < 200; i++) {
      items.add(new TextItem(valueOf(i)));
    }
    adapter.setItems(items);
    adapter.notifyDataSetChanged();
  }


  public void onAdd(View view) {
    int bottom = items.size() - 1;
    items.add(new TextItem(valueOf(currentThreadTimeMillis())));
    adapter.notifyItemInserted(bottom + 1);
    recyclerView.scrollToPosition(bottom + 1);
  }


  public void onRemove(View view) {
    if (items==null||items.size()<=0){
      return;
    }
    int bottom = items.size() - 1;
    recyclerView.scrollToPosition(bottom);
    items.remove(bottom);
    adapter.notifyItemRemoved(bottom);
  }


  public void onClear(View view) {
    items.clear();
    adapter.notifyDataSetChanged();
  }


  private class ObservableTextItemViewBinder extends ItemViewBinder<TextItem, ObservableTextItemViewBinder.TextHolder> {

    class TextHolder extends RecyclerView.ViewHolder {

      private @NonNull final TextView text;


      TextHolder(@NonNull View itemView) {
        super(itemView);
        this.text = itemView.findViewById(R.id.text);
      }
    }


    @NonNull @Override
    protected TextHolder onCreateViewHolder(@NonNull LayoutInflater inflater, @NonNull ViewGroup parent) {
      View root = inflater.inflate(R.layout.api_item_text, parent, false);
      return new TextHolder(root);
    }


    @Override
    protected void onBindViewHolder(@NonNull TextHolder holder, @NonNull TextItem textItem) {
      holder.text.setText("observable item(" + textItem.text + ")");
    }


    @Override
    protected void onViewRecycled(@NonNull TextHolder holder) {
      appendTerminalLine("onViewRecycled: " + holder.text.getText());
    }


    @Override
    protected boolean onFailedToRecycleView(@NonNull TextHolder holder) {
      appendTerminalLine("onFailedToRecycleView: " + holder.text.getText());
      return true;
    }


    @Override
    protected void onViewAttachedToWindow(@NonNull TextHolder holder) {
      appendTerminalLine("onViewAttachedToWindow: " + holder.text.getText());
    }


    @Override
    protected void onViewDetachedFromWindow(@NonNull TextHolder holder) {
      appendTerminalLine("onViewDetachedFromWindow: " + holder.text.getText());
    }


    private int buffer = 0;


    private void appendTerminalLine(String line) {
      if (buffer == 5) {
        terminal.setText(TERMINAL_DEFAULT_TEXT);
        buffer = 0;
      }
      terminal.append("\n" + line);
      buffer++;
    }
  }
}
