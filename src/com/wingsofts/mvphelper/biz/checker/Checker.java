package com.wingsofts.mvphelper.biz.checker;

import com.wingsofts.mvphelper.biz.Startable;

/**
 * The interface witch provide methods to check if current document match all conditions
 *
 * @author DengChao
 * @since 2017/4/10
 */
public interface Checker extends Startable {
    /**
     * Whether the class name end with 'Contract' or 'Presenter'
     */
    boolean hasSuffix();

    /**
     * Whether the document is in a proper package;
     */
    boolean isInRightPlace();

    /**
     * Start generation
     */
    void start();

    /**
     * The runtime mode.
     */
    enum Mode {
        CONTRACT, PRESENTER, ACTIVITY, FRAGMENT
    }
}
