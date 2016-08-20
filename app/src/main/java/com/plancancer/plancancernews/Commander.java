package com.plancancer.plancancernews;

/**
 * Created by Yazid on 07/02/2016.
 */
public interface Commander {
    public Object execute();
    public void undo();
}
