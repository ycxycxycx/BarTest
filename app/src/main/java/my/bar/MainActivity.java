package my.bar;

;
import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.view.ViewTreeObserver;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

public class MainActivity extends Activity implements ObservableScrollView.ScrollViewListener {

    private ObservableScrollView scrollView;

    private ListView listView;

    private ImageView imageView;


    private int imageHeight;

    LinearLayout ll;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ll =(LinearLayout)findViewById(R.id.ln);
        scrollView = (ObservableScrollView) findViewById(R.id.scrollview);
//        listView = (ListView) findViewById(R.id.listview);
        imageView = (ImageView) findViewById(R.id.image_view14166);
        initListeners();
    }

    private void initListeners() {
        // 获取顶部图片高度后，设置滚动监听
        ViewTreeObserver vto = imageView.getViewTreeObserver();
        vto.addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                imageView.getViewTreeObserver().removeGlobalOnLayoutListener(
                        this);
                imageHeight = imageView.getHeight();

                scrollView.setScrollViewListener(MainActivity.this);
            }
        });
    }

    @Override
    public void onScrollChanged(ObservableScrollView scrollView, int x, int y, int oldx, int oldy) {
        // TODO Auto-generated method stub
        // Log.i("TAG", "y--->" + y + "    height-->" + height);
        if (y <= 0) {
            ll.setBackgroundColor(Color.argb((int) 0, 227, 29, 26));//AGB由相关工具获得，或者美工提供
        } else if (y > 0 && y <= imageHeight) {
            float scale = (float) y / imageHeight;
            float alpha = (255 * scale);
            // 只是layout背景透明(仿知乎滑动效果)
            ll.setBackgroundColor(Color.argb((int) alpha, 255, 255, 255));
        } else {
            //第一位为透明度（0表示透明，255表示完全不透明），后三位表示颜色。
            ll.setBackgroundColor(Color.argb((int) 255, 255, 255, 255));
        }
    }
}