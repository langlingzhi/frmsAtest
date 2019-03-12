//package com.nuanshui.frms.test.controller.system;
//
//
//import com.nuanshui.frms.test.csservice.FrmsUserService;
//import com.nuanshui.frms.test.entity.cs.system.FrmsUser;
//import com.nuanshui.frms.test.entity.cs.system.FrmsUserCmd;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//
//import java.util.List;
//
//@RestController
//@RequestMapping({"/sysUser"})
//public class SysUserController
//{
//    private static final Logger log = LoggerFactory.getLogger(SysUserController.class);
//    @Autowired
//    private FrmsUserService frmsUserService;
//
//    public int deleteByPrimaryKey(@RequestBody Integer id)
//    {
//        return this.frmsUserService.deleteByPrimaryKey(id);
//    }
//
//    public int insert(@RequestBody FrmsUserCmd frmsUserCmd)
//    {
//        return this.frmsUserService.insert(frmsUserCmd);
//    }
//
//    public int insertSelective(@RequestBody FrmsUserCmd frmsUserCmd)
//    {
//        return this.frmsUserService.insertSelective(sysUserCmd);
//    }
//
//    public FrmsUser selectByPrimaryKey(@RequestBody Integer id)
//    {
//        return this.frmsUserService.selectByPrimaryKey(id);
//    }
//
//    public int updateByPrimaryKeySelective(@RequestBody FrmsUser frmsUser)
//    {
//        return this.frmsUserService.updateByPrimaryKeySelective(frmsUser);
//    }
//
//    public int updateByPrimaryKey(@RequestBody FrmsUser frmsUser)
//    {
//        return this.frmsUserService.updateByPrimaryKey(frmsUser);
//    }
//
//    public int queryCheckUser(@RequestBody String name)
//    {
//        return this.sysUserMapper.queryCheckUser(name);
//    }
//
//    public int queryCheckPwd(@RequestBody SysUserCmd sysUserCmd)
//    {
//        return this.sysUserMapper.queryCheckPwd(sysUserCmd);
//    }
//
//    public SysUser findBySysUser(@RequestBody SysUserCmd sysUserCmd)
//    {
//        return this.sysUserMapper.findBySysUser(sysUserCmd);
//    }
//
//    public int updateByNoSelective(@RequestBody SysUserCmd sysUserCmd)
//    {
//        return this.sysUserMapper.updateByNoSelective(sysUserCmd);
//    }
//
//    public List<SysUser> findAllSysUser(@RequestBody SysUserCmd sysUserCmd)
//    {
//        return this.sysUserMapper.findAllSysUser(sysUserCmd);
//    }
//}
