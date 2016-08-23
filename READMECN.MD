# MVPHelper
一款Intellj IDEA 和Android Studio的插件，可以为MVP生成接口以及实现类，解放双手。
![image](https://github.com/githubwing/MVPHelper/raw/master/img/mvp_presenter.gif)

##注意
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
当你在Contract类中使用插件，效果如下：

![image](https://github.com/githubwing/MVPHelper/raw/master/img/mvp_contract.gif)

##如何使用
###第一步
下载[MVPHelper.jar](https://github.com/githubwing/MVPHelper/raw/master/MVPHelper.jar)
###第二步
安装插件

![image](https://github.com/githubwing/MVPHelper/raw/master/img/step-1.png)
![image](https://github.com/githubwing/MVPHelper/raw/master/img/step0.png)
##Step3
在Contract类或者Presenter类内部，点击Generate菜单，选择MVPHelper即可生成对应文件

![image](https://github.com/githubwing/MVPHelper/raw/master/img/step1.png)
![image](https://github.com/githubwing/MVPHelper/raw/master/img/step2.png)

#Eclipse

[![GO HOME](http://ww4.sinaimg.cn/large/5e9a81dbgw1eu90m08v86j20dw09a3yu.jpg)
# License

    Copyright 2016 androidwing1992

    Licensed under the Apache License, Version 2.0 (the "License");
    you may not use this file except in compliance with the License.
    You may obtain a copy of the License at

        http://www.apache.org/licenses/LICENSE-2.0

    Unless required by applicable law or agreed to in writing, software
    distributed under the License is distributed on an "AS IS" BASIS,
    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
    See the License for the specific language governing permissions and
    limitations under the License.

