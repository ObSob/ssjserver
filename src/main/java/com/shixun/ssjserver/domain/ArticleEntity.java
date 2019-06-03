package com.shixun.ssjserver.domain;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "article")
public class ArticleEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column
    private String title;

    @Column
    private String content;

    @Column
    private Integer userid;

    @Column
    private Integer isupload;

    @Column
    private Integer isShared;

    @Column
    private String savetime;

    @Column
    private Integer version;

    @Column(unique = true)
    private String code;

    public ArticleEntity(){}

    public ArticleEntity(String title, String content, Integer userid, Integer isupload, Integer isShared, String savetime, Integer  version, String code)
    {
        this.title = title;
        this.content = content;
        this.userid = userid;
        this.isupload = isupload;
        this.isShared = isShared;
        this.savetime = savetime;
        this.version = version;
        this.code = code;
    }
}
