# NOTE THIS IS A TRANSLATED COPY OF [中文文档](/README.md), MAY OUT OF DATE. 

Thanks for [XieEDeHeiShou](https://github.com/XieEDeHeiShou)
# MvpHelper
A plugin that designed for ```Intellij IDEA``` and ```Android Studio```, used to generate Mvp interfaces and classes.

```Issue Report``` welcomed, ```Feature Request``` welcomed, ```Pull Request``` welcomed

## Preview
```Contract``` mode：

![mode_contract](img/mode_contract.gif)

```Presenter``` mode(with suffix 'Impl'):

![mode_presenter](img/mode_presenter.gif)

Suffix support:

![suffix_support](img/suffix_support.gif)

Log (unexpected balloon may appear, just disable it in the Event Log):
![log](img/log.gif)

Error dialog:

![error_package](img/error_package.gif)

![error_class_name](img/error_class_name.gif)

## Note
For Mvp, define an interface ```Contract``` to place ```Model``` , ```View``` , ```Presenter``` 
will greatly reduce the number of source files:

    public interface GoodsInfoContract {
    
        interface View {

        }
        interface Presenter {

        }
        interface Model {

        }

    }

## How to install
### Search in the repositories with keyword:MvpHelper
![image](img/install_repositories.png)

### Or install plugin.jar
#### step1
Download [MvpHelper_v2_3_release.jar](MVPHelper_v2_3_release.jar)
or redirect to [releases](https://github.com/XieEDeHeiShou/MVPHelper/releases/latest)

#### step2
Install it.

![image](img/install_local.png)

## How to use
In ```Contract``` or ```Presenter``` ，click ```Generate``` menu or try hot-key ```Alt + Insert```, 
select ```Mvp Helper``` .

## Feature
+ Remove hot-key ```Meta + 1```
+ MvpHelper menu will not appear in un-java files when use hot key ```Alt + Insert```
+ No more root package limit (v1.0 only support com,org,me...)
+ No more ```contract``` package location limit, and support sub-package:
  >```com.example.project.package1.contract.package2.AContract``` 
  will generate ```com.example.project.package1.model.package2.AModel```
  and ```com.example.project.package1.presenter.package2.APresenter``` 
+ While ```AContract``` under ```presenter``` package, an error dialog will occur.(used to generate wrong files)
+ Support File Header Template
+  ```Contract``` mode achieved
  + ```Contract``` MUST under a path witch has at least one package END WITH ```contract```
  + Force ```Contract``` to be a ```public interface``` 
  + Remove unnecessary modifier form inner interfaces of ```Contract```
  + Re-generate will delete old files then generate new files.
+ ```Presenter``` mode achieved
  + ```Presenter``` MUST under a path witch has at least one package END WITH ```presenter```
  + When launch with```Presenter``` mode, it will call ```Contract``` mode later.
  + When launch with```Presenter``` mode with custom suffix, the old ```Presenter```, which without suffix,
   will not be delete automatically. So we recommend ```Contract``` mode.
## TODO
+ Achieve ```Activity``` mode
+ Achieve ```Fragment``` mode
+ Add generated files to git automatically (may take long time to learn command grammar of git)

## Related reference
+ [Intellij Platform SDK DevGuide](http://www.jetbrains.org/intellij/sdk/docs/)
  + [Persisting State of Components](http://www.jetbrains.org/intellij/sdk/docs/basics/persisting_state_of_components.html)
   How to save and load plugin configs
+ [Customizing the IDEA Settings Dialog](https://confluence.jetbrains.com/display/IDEADEV/Customizing+the+IDEA+Settings+Dialog)
+ [IDEA Online Source Code](https://upsource.jetbrains.com/idea-ce)
  + [CreateClassAction](https://upsource.jetbrains.com/idea-ce/file/idea-ce-10df87d7a9840e5901d4901ac4fff7ba035501c2/java/java-impl/src/com/intellij/ide/actions/CreateClassAction.java)
+ [ApplicationConfigurable, ProjectConfigurable](http://corochann.com/intellij-plugin-development-introduction-applicationconfigurable-projectconfigurable-873.html)  
+ [PersistStateComponent](http://corochann.com/intellij-plugin-development-introduction-persiststatecomponent-903.html)
+ [Java Api Sample](http://www.programcreek.com/java-api-examples/index.php)
+ [Moxun's bolg](https://moxun.me/archives/category/黑科技/idea插件开发) (Chinese blog)
+ [AndroidStudio/IDEA插件开发学习](http://www.jianshu.com/p/0117d4b1eb00) (Chinese blog)

## Change log
### v2.x -Based on Intellij Open Api, by [XieEDeHeiShou](https://github.com/XieEDeHeiShou)
+ 2017-07-19
  + Fix NPE(#20)
  + MvpHelper_v2_3_release.jar
+ 2017-04-27
  + Fix generated class modifier bug:package-private -> public
  + Add EventLogger
  + MvpHelper_v2_2_release.jar
+ 2017-04-25
  + Save and load configurations.
  + Load config while running.
  + MvpHelper_v2_0_release.jar
  + Add guide.gif
  + Pull request to [Original Repo](https://github.com/githubwing/MVPHelper)
  + Test under Android Studio Environment with Java project and Android project
  + Fix bug caused by merge: generated ```Contract``` lose ```public``` modifier, force to be ```public interface PrefixContract```
  + Fix ```AbstractMethodException``` caused by platform resource release
  + MvpHelper_v2_1_release.jar
+ 2017-04-24
  + Layout config panel
  + Achieve ```PresenterModeDirGenerator```
  + Extract interface, re-package
  + Change the way to update ```Contract```
    + force ```Contract``` to be ```interface```
    + Remove unnecessary modifier form inner interfaces of ```Contract```
+ 2017-04-23 
  + Achieve file generation , package inject, support File Head Template
  + Achieve generated class implement special interface
  + Update README.CN.MD(Removed later)
+ 2017-04-15 Update Environment check rule 
+ 2017-04-12 Achieve plugin visibility control, when launched by hot-key ```Alt + Insert``` 
  ,if current file is not a javaFile.java, the plugin menu will be invisible.
+ 2017-04-11 Achieve ```DirGenerator``` and ```FileGenerator``` used to generate dirs and files
+ 2017-04-10 Achieve ```EnvironmentChecker``` used to check plugin runtime condition:
  + Is the document END WITH ```Contract``` and UNDER a ```contract``` package
  + Is the document END WITH ```Presneter``` and UNDER a ```presenter``` package
  
### v1.x -Based on Java IO Api, by [githubwing](https://github.com/githubwing)
+ 2017-04-10 Try to rebuild the project with Intellij Open Api.
+ 2017-04-09 Remove ```C``` smell form original source code.
+ 2017-04-06 Fork form [original repe](https://github.com/githubwing/MVPHelper), add comment lines for reading.
  
## Eclipse
![GO HOME](./img/go_home_you_are_drunk.png)

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
