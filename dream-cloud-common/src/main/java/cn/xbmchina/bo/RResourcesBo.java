package cn.xbmchina.bo;

import cn.xbmchina.domain.Resources;

public class RResourcesBo extends Resources {
    /**
     *
     */
    private static final long serialVersionUID = 1L;
    private String checked;//是否选中

    public String getChecked() {
        return checked;
    }

    public void setChecked(String checked) {
        this.checked = checked;
    }

}