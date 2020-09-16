
import com.iistd.IistdApplication;
import com.iistd.dao.SearchDao;
import com.iistd.entity.Search;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;



@RunWith(SpringRunner.class)
@SpringBootTest(classes={IistdApplication.class})// 指定启动类
public class esTest {


    @Autowired
    SearchDao searchDao;



    @Test
    public void test111(){
        Search search = new Search();
        search.setTitle("《Java编程思想》");
        search.setContent("适合对象：java初级、中级；偏重编程思想，java如果没有基础看此书会有点晦涩难懂，枯燥乏味。当你有些编程经验之后，再来看这本书。多写代码，多思考会对你的编程思想有很大提升。");
        search.setCreateTime("2020-09-13");
        searchDao.save(search);
    }
    @Test
    public void test(){
        Search search = new Search();
        search.setTitle("《Java编程思想》");
        search.setContent("适合对象：java初级、中级；偏重编程思想，java如果没有基础看此书会有点晦涩难懂，枯燥乏味。当你有些编程经验之后，再来看这本书。多写代码，多思考会对你的编程思想有很大提升。");
        search.setCreateTime("2020-09-13");
        searchDao.save(search);

        search.setTitle("《Effective Java》");
        search.setContent("适合对象：javajava初级、中级；同样是翻译版，虽有些瑕疵，不影响阅读和体验。不过作为进阶技术书籍，读懂它和分辨出瑕疵来，也说明你的功力更进一步了。");
        search.setCreateTime("2020-09-13");
        searchDao.save(search);

        search.setTitle("《数据库原理》");
        search.setContent("适合对象：javajava初级、中级、高级；数据库也是必然要掌握的一门学科。作为java初级和中级推荐一下，把高级也列进来是个人觉得，我们大部分人在工作中都只是在设计程序初始，会用到数据库方面的知识：建库、建表、索引、存储过程等。殊不知，数据库在系统中起着举足轻重的作用，大到引起系统崩溃，小到页面数据查询异常等。值得重视！");
        search.setCreateTime("2020-09-13");
        searchDao.save(search);

        search.setTitle("《Java与模式》");
        search.setContent("适合对象：java初级、中级、高级；设计模式，可作为入门和进阶的过渡学习；也可作为进阶到高级的学习。");
        search.setCreateTime("2020-09-13");
        searchDao.save(search);

        search.setTitle("《重构：改善既有代码的设计》");
        search.setContent("适合对象：中级、高级；当你大大小小经历了一些项目之后，想要针对某些项目做些改善或重构，那么：这本书特别适合你。");
        search.setCreateTime("2020-09-13");
        searchDao.save(search);

        search.setTitle("《代码整洁之道》");
        search.setContent("适合对象：中级、高级；当你经历了一些项目，也撸了（复制+粘贴）不少代码之后；你要做的是要想办法提升你写的代码的效率和性能以及整洁等。");
        search.setCreateTime("2020-09-13");
        searchDao.save(search);

        search.setTitle("《HTTP权威指南》");
        search.setContent("适合对象：中级、高级；这本书可以让你对http通信机制原理，网络传输方面来个一站式的学些。彻底掌握web开发过程中，通信机制原理和技术。");
        search.setCreateTime("2020-09-13");
        searchDao.save(search);

        search.setTitle("《jQuery基础教程》");
        search.setContent("适合对象：java初级、中级、高级；这些是作为学习Java Web开发来说，前端技术和框架的最好典范了。jq、js、xml等；虽然现在前端技术发展到vue、anglar那些了。");
        search.setCreateTime("2020-09-13");
        searchDao.save(search);

        search.setTitle("《Java并发编程实践》");
        search.setContent(" 适合对象：中级、高级；做大型高并发多线程系统时，必不可少的技术：并发编程。");
        search.setCreateTime("2020-09-13");
        searchDao.save(search);

        search.setTitle("《Spring实战（第4版）》");
        search.setContent(" 适合对象：中级、高级；高级阶段，必须学会使用并掌握web框架的原理和技术知识；Spring作为web框架中重中之重。是必须要掌握的技术。");
        search.setCreateTime("2020-09-13");
        searchDao.save(search);

        search.setTitle("《深入理解Java 虚拟机 第2版》");
        search.setContent("适合对象：中级、高级；一个合格的java程序员，对jvm深层原理如果不了解；甚至很陌生。那么，想要在这条道路上继续深入发展的话。赶紧学一学jvm的原理知识吧。");
        search.setCreateTime("2020-09-13");
        searchDao.save(search);


    }
}
