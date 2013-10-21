package com.qsoft.OnlineDio.Model;

/**
 * User: Dell 3360
 * Date: 10/20/13
 * Time: 8:39 AM
 */
public class HomeModel
{
    private String title;
    private String userName;
    private String like;
    private String comment;
    private String time;

    public HomeModel(String title, String userName, String like, String comment, String time)
    {
        this.title = title;
        this.userName = userName;
        this.like = like;
        this.comment = comment;
        this.time = time;
    }

    public String getTitle()
    {
        return title;
    }

    public void setTitle(String title)
    {
        this.title = title;
    }

    public String getUserName()
    {
        return userName;
    }

    public void setUserName(String userName)
    {
        this.userName = userName;
    }

    public String getLike()
    {
        return like;
    }

    public void setLike(String like)
    {
        this.like = like;
    }

    public String getComment()
    {
        return comment;
    }

    public void setComment(String comment)
    {
        this.comment = comment;
    }

    public String getTime()
    {
        return time;
    }

    public void setTime(String time)
    {
        this.time = time;
    }
}
