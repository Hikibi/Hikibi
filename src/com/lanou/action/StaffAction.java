package com.lanou.action;

import com.lanou.domain.Department;
import com.lanou.domain.Post;
import com.opensymphony.xwork2.ActionSupport;
import net.sf.json.JSONArray;
import net.sf.json.JsonConfig;
import org.apache.struts2.ServletActionContext;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by dllo on 17/10/16.
 */
public class StaffAction extends ActionSupport{

//    存储所有部门的集合
    private List<Department> departList;
    private List<Post> postList;

    {

//        初始化部门集合数据
        departList = new ArrayList<>();

//        部门添加
        for (int i = 0; i < 3; i++) {
            Department department = new Department();
//            部门 ID
            department.setId(i);
//            部门名字
            department.setDname("部门" + i);

//            存储当前部门的所有职务集合
            List<Post> postList = new ArrayList<>();

//            部门中的职务集合添加
            for (int j = 0; j < 5; j++) {
                Post post = new Post();
//                职务 ID
                post.setId(j);
//                职务名称
                post.setPname(i + "职务" + j);

//                将职务加到职务集合中
                postList.add(post);
            }

//            将职务集合设置到部门对象中
            department.setPostList(postList);

//            将部门对象添加到部门集合中
            departList.add(department);

        }

    }



    //    查找所有部门
    public String findDepartment(){
        return SUCCESS;
    }

//    发起 部门id 查询时传递过来的 部门id
    private String departId;


    /**
     * 根据部门 id 查找职务集合
     */
    public String findPostByPid() throws IOException {
        System.out.println("要查询的部门 id --->>" + departId);

        int dId = Integer.parseInt(departId);

//        根据 部门id 获取该部门下的所有职务集合
        List<Post> postList = departList.get(dId).getPostList();

//        封装 json 数据
        JsonConfig jsonConfig = new JsonConfig();

        String jsonArray = JSONArray.fromObject(postList, jsonConfig).toString();


//        将 json 数据作为响应内容进行返回
        ServletActionContext.getResponse().setContentType("application/json;charset=utf-8");
        ServletActionContext.getResponse().getWriter().print(jsonArray);

//        什么都不做
        return null;
    }

    /**
     * 根据 struts2 返回 json 数据
     */
    public String findPostListByPid2(){

        int dID = Integer.parseInt(departId);
        postList = departList.get(dID).getPostList();

        return SUCCESS;
    }





    public String getDepartId() {
        return departId;
    }

    public void setDepartId(String departId){
        this.departId = departId;
    }

    public List<Department> getDepartList() {
        return departList;
    }

    public void setDepartList(List<Department> departList) {
        this.departList = departList;
    }

    public List<Post> getPostList() {
        return postList;
    }

    public void setPostList(List<Post> postList) {
        this.postList = postList;
    }
}
