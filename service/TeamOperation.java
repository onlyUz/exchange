package service;

import company.Architect;
import company.Designer;
import company.Employee;
import company.Programmer;
import service.Status;
import service.TeamException;

import java.util.ArrayList;
import java.util.List;

public class TeamOperation {
    private static int counter = 1; //给memberId赋值使用
    private final int MAX_MEMBER = 5;//限制开发团队人数
    private List<Programmer> team = new ArrayList<>();//保存团队开发成员

    public TeamOperation() {
    }

    public List<Programmer> getTeam() {
        return team;
    }

    // 添加成员
    public void addMember(Employee e) throws TeamException {
        // 成员已满，无法添加
        if(team.size() >= MAX_MEMBER) {
            throw new TeamException("成员已满，无法添加");
        }

        //该成员不是开发人员，无法添加
        if (!(e instanceof Programmer)) {
            throw new TeamException("该成员不是开发人员，无法添加");
        }
        //该员工已在本开发团队中
        if (isExist(e)) {
            throw new TeamException("该员工已在本开发团队中");
        }

        Programmer p = (Programmer) e;

        // 该员工已是某团队成员
        //该员正在休假，无法添加
        if ("BUSY".equals(p.getStatus())) {
            throw new TeamException("该员工已是某团队成员");
        } else if ("VOCATION".equals(p.getStatus())) {
            throw new TeamException("该员正在休假，无法添加");
        }
        // 团队中至多只能有一名架构师
        // 团队中至多只能有两名设计师
        // 团队中至多只能有三名程序员

        // 获取team已有成员中架构师、设计师、程序员的人数
        int numOfArch = 0, numOfDes = 0, numOfPro = 0;
        for (Employee employee : team) {
            if (employee instanceof Architect) {
                numOfArch++;
            } else if (employee instanceof Designer) {
                numOfDes++;
            } else if (employee instanceof Programmer) {
                numOfPro++;
            }
        }

        if (p instanceof Architect) {
            if (numOfArch > 2) {
                throw new TeamException("团队中至多只能有一名架构师");
            }
        } else if (p instanceof Designer) {
            if (numOfDes > 3) {
                throw new TeamException("团队中至多只能有两名设计师");
            }
        } else {
            if (numOfDes > 4) {
                throw new TeamException("团队中至多只能有三名程序员");
            }
        }

        //将p添加到team中
        team.add(p);
        p.setStatus(Status.BUSY);
        p.setMemberId(counter++);

    }

    // 判断指定的员工是否已经存在于现有的开发团队中
    private boolean isExist(Employee e) {
        for (Programmer employee : team) {
            if (employee.getId() == e.getId()) {
                return true;
            }
        }
        return false;
    }

    // 从团队中删除成员
    public void removeMember(int memberId) throws TeamException {
        int i = 0;
        for (; i < team.size(); i++) {
            if (team.get(i).getMemberId() == memberId) {
                team.get(i).setStatus(Status.FREE);
                break;
            }
        }
        // 没找到报错
        if (i == team.size()) {
            throw new TeamException("找不到指定memberId的员工，删除失败");
        }
        // 删除
        team.remove(i);


    }
}
