package com.github.tangyi.user.enums;

import lombok.Getter;

/**
 * 附件存储类型枚举类
 *
 * @author zdz
 * @date 2022/04/12 00:22
 */
@Getter
public enum AttachUploaderEnum {

    /**
     * 文件
     */
    FILE(1, "文件", "com.github.tangyi.user.uploader.FileUploader"),

    /**
     * Fast DFS
     */
    FAST_DFS(2, "FastDfs", "com.github.tangyi.user.uploader.FastDfsUploader"),

    /**
     * 七牛云
     */
    QI_NIU(3, "七牛云", "com.github.tangyi.user.uploader.QiNiuUploader");

    /**
     * 值（1，2，3）
     */
    private Integer value;

    /**
     * 描述（文件，FastDfs，七牛云）
     */
    private String desc;

    /**
     * 实现类名
     */
    private String implClass;

    /**
     * 构造器
     *
     * @param value     值
     * @param desc      描述
     * @param implClass 实现类名
     */
    AttachUploaderEnum(int value, String desc, String implClass) {
        this.value = value;
        this.desc = desc;
        this.implClass = implClass;
    }

    /**
     * 根据所给的值匹配附件存储类型
     *
     * @param value 附件存储类型值
     * @return 附件存储类型
     */
    public static AttachUploaderEnum matchByValue(Integer value) {
        for (AttachUploaderEnum item : AttachUploaderEnum.values()) {
            if (item.value.equals(value)) {
                return item;
            }
        }
        return FILE;
    }

}
