package leavesc.hello.databindingsamples;

import android.databinding.DataBindingUtil;
import android.databinding.ObservableArrayList;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import leavesc.hello.databindingsamples.databinding.ActivityMain14Binding;

/**
 * 测试 DataBinding And RecyclerView
 * 参考：
 * [Android DataBinding 从入门到进阶](https://www.jianshu.com/p/2c4ac24761f5)
 * [一行Java代码实现RecyclerView的Adapter?一行都不需要！](https://www.jianshu.com/p/c69b0e4e18f1)
 * [官方例子](https://github.com/googlesamples/android-databinding)
 *
 * @author Demons96
 * create at 19.4.13 17:34
 */
public class Main14Activity extends AppCompatActivity {
    public final ObservableArrayList<Student> students = new ObservableArrayList<>();

    /**
     * 初始化数据源
     */
    {
        for (int i = 0; i < 20; i++) {
            students.add(new Student("学生:" + i));
        }
        students.addAll(students);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main14);

        ActivityMain14Binding binding = DataBindingUtil.setContentView(this, R.layout.activity_main14);
        binding.setActivity(this);
    }

    /**
     * 数据的实体类
     */
    public class Student {
        public String name;
        public Student(String name) {
            this.name = name;
        }
    }
}
