package com.ch.ebusiness.service.store;

import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.ch.ebusiness.entity.BUser;
import com.ch.ebusiness.entity.Store;
import com.ch.ebusiness.repository.store.SGoodsRepository;
import com.ch.ebusiness.service.admin.GoodsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.multipart.MultipartFile;

import com.ch.ebusiness.entity.Goods;
import com.ch.ebusiness.repository.admin.GoodsRepository;
import com.ch.ebusiness.util.MyUtil;
@Service
public class SGoodsServiceImpl implements SGoodsService {
    @Autowired
    private SGoodsRepository sGoodsRepository;

    @Override
    public String selectAllGoodsByPage(Model model, int currentPage, String act) {
        //共多少个商品
        int totalCount = sGoodsRepository.selectAllGoods();
        //计算共多少页
        int pageSize = 5;
        int totalPage = (int)Math.ceil(totalCount*1.0/pageSize);
        List<Goods> typeByPage = sGoodsRepository.selectAllGoodsByPage((currentPage-1)*pageSize, pageSize);
        model.addAttribute("allGoods", typeByPage);
        model.addAttribute("totalPage", totalPage);
        model.addAttribute("currentPage", currentPage);
        model.addAttribute("act", act);
        return "admin/selectGoods";
    }

    @Override
    public String addGoods(Goods goods, HttpServletRequest  request, String act, HttpSession session) throws IllegalStateException, IOException {
        MultipartFile myfile = goods.getFileName();
        //如果选择了上传文件，将文件上传到指定的目录images
        if(!myfile.isEmpty()) {
            //上传文件路径（生产环境）
            //String path = request.getServletContext().getRealPath("/images/");
            //获得上传文件原名
            //上传文件路径（开发环境）
            String path = "F:\\文件\\2021.大三下学期\\软件开发实践\\源代码\\eBusiness\\src\\main\\resources\\static\\images";
            //获得上传文件原名
            String fileName = myfile.getOriginalFilename();
            //对文件重命名
            String fileNewName = MyUtil.getNewFileName(fileName);
            File filePath = new File(path + File.separator + fileNewName);
            //如果文件目录不存在，创建目录
            if(!filePath.getParentFile().exists()) {
                filePath.getParentFile().mkdirs();
            }
            //将上传文件保存到一个目标文件中
            myfile.transferTo(filePath);
            //将重命名后的图片名存到goods对象中，添加时使用
            //使用新上传的会出现无法显示的情况
            goods.setGpicture(fileName);
        }
        if("add".equals(act)) {
            int sid = sGoodsRepository.isStore(MyUtil.getUser(session).getId()).get(0).getId();
            goods.setStore_id(sid);
            int n = sGoodsRepository.addGoods(goods);
            if(n > 0)//成功
                return "redirect:/";
            //失败
            return "redirect:/";
        }else {//修改
            int n = sGoodsRepository.updateGoods(goods);
            if(n > 0)//成功
                return "redirect:/";
            //失败
            return "redirect:/";
        }
    }
    @Override
    public String toAddGoods(@ModelAttribute("store") Store store, Goods goods, Model model, Integer uid) {
        List<BUser> list = sGoodsRepository.isStore(uid);
        if(list.size() >= 1) {
            model.addAttribute("goodsType", sGoodsRepository.selectAllGoodsType());
            return "store/addGoods";
        }
        else{
            return "store/register";
        }
    }

    @Override
    public String detail(Model model, Integer id, String act) {
        model.addAttribute("goods", sGoodsRepository.selectAGoods(id));
        if("detail".equals(act)) {
            return "store/detail";
        } else {
            model.addAttribute("goodsType", sGoodsRepository.selectAllGoodsType());
            return "store/updateAGoods";
        }
    }

    @Override
    public String delete(Integer id) {
        if(sGoodsRepository.selectCartGoods(id).size() > 0
                || sGoodsRepository.selectFocusGoods(id).size() > 0
                || sGoodsRepository.selectOrderGoods(id).size() > 0) {
            return "no";
        } else {
            sGoodsRepository.deleteAGoods(id);
            return "/store/selectMyGoods?currentPage=1&act=deleteSelect";
        }
    }

}
