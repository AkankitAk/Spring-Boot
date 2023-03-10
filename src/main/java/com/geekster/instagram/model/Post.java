package com.geekster.instagram.model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.sql.Timestamp;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "tbl_post")
public class Post {
    @Id
    @Column(name = "post_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer postId;
    @Column(name = "create_date")
    private Timestamp createdDate;
    @Column(name = "update_date")
    private Timestamp updateDate;
    @Column(name = "post_data")
    private String postData;
    @JoinColumn(name = "user_id")
    @ManyToOne(fetch = FetchType.LAZY)
    private User user;

    @JoinColumn(name = "status_id")
    @ManyToOne
    private Status statusId;
    @JoinColumn(name = "security_id")
    @ManyToOne
    private Security securityId;

}
