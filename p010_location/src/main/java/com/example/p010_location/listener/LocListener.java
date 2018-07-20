package com.example.p010_location.listener;

import com.example.p010_location.bean.LocationBean;

public interface LocListener {
    void success(LocationBean model);
    void fail(int msg);
}
