package com.wingsofts.mvphelper;

/**
 * class文件的实体
 * Created by wing on 16/7/23.
 */
public class ClassModel {
    private String _className;
    private String _classFullName;

    public String get_classFullName() {
        return _classFullName;
    }

    public void set_classFullName(String _classFullName) {
        this._classFullName = _classFullName;
    }

    public String get_className() {
        return _className;
    }

    public void set_className(String _className) {
        this._className = _className;
    }
}
