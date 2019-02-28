package weibo;

import weibo.bean.Text;

import java.io.*;
import java.util.List;

/**
 * @author 郑煜
 * @Title: demo
 * @ProjectName weibo
 * @Description: TODO
 * @date 2019/1/2116:46
 */
public class demo {
    //  userid;password;birthday;username;sex;address;profession;
    //id;username;photo;information;time;likes;comments;pid;List<Text> childContent;
    /*
    CREATE TABLE `textfirst` (
  `id` int(10) NOT NULL AUTO_INCREMENT,
  `username` varchar(100) DEFAULT NULL,
  `photo` varchar(100) DEFAULT NULL,
  `information` varchar(300) CHARACTER SET utf8mb4 DEFAULT NULL,
  `likes` int(10) DEFAULT NULL,
  `times` varchar(50) DEFAULT NULL,
  `comment` int(10) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
     */
    /*
    CREATE TABLE `textsecond` (
  `id` int(10) NOT NULL AUTO_INCREMENT,
  `username` varchar(100) DEFAULT NULL,
  `photo` varchar(100) DEFAULT NULL,
  `information` varchar(300) CHARACTER SET utf8mb4 DEFAULT NULL,
  `likes` int(11) DEFAULT NULL,
  `times` varchar(100) DEFAULT NULL,
  `pidf` int(10) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
     */

    public static void main(String[] args) {
            String url="people/tou";
        System.out.println(url.replace("people/",""));
    }
}


