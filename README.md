# MVPHelper
A plugin for Intellj IDEA &amp;&amp; Android Studio. It can help you create interfaces and classes of MVP.
##[中文说明](https://github.com/githubwing/MVPHelper/blob/master/READMECN.MD)
![image](https://github.com/githubwing/MVPHelper/raw/master/img/mvp_presenter.gif)

##Tips

You can create a 'contract' class,It contains View,Presenter and Model,it can make your '.java' files less. 
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
So if you use helper in Contract it will

![image](https://github.com/githubwing/MVPHelper/raw/master/img/mvp_contract.gif)

##How To Use
###Step1
Download[MVPHelper.jar](https://github.com/githubwing/MVPHelper/raw/master/MVPHelper.jar)
###Step2
install plugin.

![image](https://github.com/githubwing/MVPHelper/raw/master/img/step-1.png)
![image](https://github.com/githubwing/MVPHelper/raw/master/img/step0.png)
##Step3
in Contract or Presenter class, Generate --> MVPHelper

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

