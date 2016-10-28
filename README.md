# MVPHelper
A plugin for Intellj IDEA &amp; Android Studio which can help you create interfaces and classes of MVP.

### 目前包名判断是以 cn/com/org/net/me/io 开头判断的，如大家的项目(非demo)有其他情况(例如me.ele.eleme)，会导致import报错等问题，请发起issues告知包名开头，来修改统计。
##[中文文档](https://github.com/githubwing/MVPHelper/blob/master/READMECN.MD)

![image](https://github.com/githubwing/MVPHelper/raw/master/img/mvp_presenter.gif)



##Tips

Creating a 'contract' class containing View,Presenter and Model won't create too many source files. 
```
public interface GoodsInfoContract {
    
  public interface View{

  }
  public interface Presenter{

  }
  public interface Model{

  }

}
```
Use this helper in Contract, it will

![image](https://github.com/githubwing/MVPHelper/raw/master/img/mvp_contract.gif)

##How To Use
### Searth 'MVPHelper' in Repositories

 ![image](https://github.com/githubwing/MVPHelper/raw/master/img/repositories.png)



# or



###Step1

Download [MVPHelper.jar](https://github.com/githubwing/MVPHelper/raw/master/MVPHelper.jar)
###Step2
install plugin.

![image](https://github.com/githubwing/MVPHelper/raw/master/img/step-1.png)
![image](https://github.com/githubwing/MVPHelper/raw/master/img/step0.png)
##Step3
Click Generate --> MVPHelper in Contract or Presenter class.

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
