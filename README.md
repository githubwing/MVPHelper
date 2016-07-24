# MVPHelper
An plugin for Intellj IDEA &amp;&amp; Android Studio. It can help you create Contract of MVP.
一款Intellj IDEA 和Android Studio 自动生成MVP模式所需接口以及实现类的插件。

![image](https://github.com/githubwing/MVPHelper/raw/master/perview.gif)
#如何使用

对于MVP模式，定义一个Contract类来放置Model View Presenter 的接口，将大大减少类文件。将普通的接口替代如下：
```
public class GoodsInfoContract {
    
  public interface GoodsInfoView{

  }
  public interface GoodsInfoPresenter{

  }
  public interface GoodsInfoModel{

  }

}
```
在Contract类内部，点击Generate菜单，选择MVPHelper即可生成对应文件、
##Step1
![image](https://github.com/githubwing/MVPHelper/raw/master/img/step1.png)
##Step2
![image](https://github.com/githubwing/MVPHelper/raw/master/img/step2.png)
