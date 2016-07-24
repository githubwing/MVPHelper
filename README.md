# MVPHelper
An plugin for Intellj IDEA &amp;&amp; Android Studio. It can help you create contract and classes of MVP.

一款Intellj IDEA 和Android Studio 自动生成MVP模式所需接口以及实现类的插件。

![image](https://github.com/githubwing/MVPHelper/raw/master/preview.gif)

##注意
对于MVP模式，定义一个Contract类来放置Model View Presenter 的接口，将大大减少类文件。将普通的接口替代如下：

You can create a 'contract' class,it can make your '.java' files less. 
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
##如何使用 How To Use
##Step1
下载[MVPHelper.jar](https://github.com/githubwing/MVPHelper/raw/master/MVPHelper.jar)

Download[MVPHelper.jar](https://github.com/githubwing/MVPHelper/raw/master/MVPHelper.jar)
##Step2
按照提示安装jar。

install plugin.

![image](https://github.com/githubwing/MVPHelper/raw/master/img/step-1.png)
![image](https://github.com/githubwing/MVPHelper/raw/master/img/step0.png)
##Step3
在Contract类内部，点击Generate菜单，选择MVPHelper即可生成对应文件

in Contract class, Generate --> MVPHelper

![image](https://github.com/githubwing/MVPHelper/raw/master/img/step1.png)
![image](https://github.com/githubwing/MVPHelper/raw/master/img/step2.png)

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

