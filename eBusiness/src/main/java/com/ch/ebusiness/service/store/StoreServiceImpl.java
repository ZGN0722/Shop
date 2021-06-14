package com.ch.ebusiness.service.store;

import com.ch.ebusiness.entity.BUser;
import com.ch.ebusiness.entity.Goods;
import com.ch.ebusiness.entity.Store;
import com.ch.ebusiness.repository.store.StoreRepository;
import com.ch.ebusiness.util.MyUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Map;

@Service
public class StoreServiceImpl implements StoreService{

    @Autowired
    private StoreRepository storeRepository;

    @Override
    public String register(Store store, Model model, HttpSession session) throws IllegalStateException, IOException  {
        MultipartFile myfile1 = store.getFileName1();
        //如果选择了上传文件，将文件上传到指定的目录images
        if(!myfile1.isEmpty()) {
            //上传文件路径（生产环境）
            //String path = request.getServletContext().getRealPath("/images/");
            //获得上传文件原名
            //上传文件路径（开发环境）
            String path = "F:\\文件\\2021.大三下学期\\软件开发实践\\源代码\\eBusiness\\src\\main\\resources\\static\\images";
            //获得上传文件原名
            String fileName = myfile1.getOriginalFilename();
            //对文件重命名
            String fileNewName = MyUtil.getNewFileName(fileName);
            File filePath = new File(path + File.separator + fileNewName);
            //如果文件目录不存在，创建目录
            if(!filePath.getParentFile().exists()) {
                filePath.getParentFile().mkdirs();
            }
            //将上传文件保存到一个目标文件中
            myfile1.transferTo(filePath);
            //将重命名后的图片名存到goods对象中，添加时使用
            store.setSpicture(fileName);
        }

        MultipartFile myfile2 = store.getFileName2();
        //如果选择了上传文件，将文件上传到指定的目录images
        if(!myfile2.isEmpty()) {
            //上传文件路径（生产环境）
            //String path = request.getServletContext().getRealPath("/images/");
            //获得上传文件原名
            //上传文件路径（开发环境）
            String path = "F:\\文件\\2021.大三下学期\\软件开发实践\\源代码\\eBusiness\\src\\main\\resources\\static\\images";
            //获得上传文件原名
            String fileName = myfile2.getOriginalFilename();
            //对文件重命名
            String fileNewName = MyUtil.getNewFileName(fileName);
            File filePath = new File(path + File.separator + fileNewName);
            //如果文件目录不存在，创建目录
            if(!filePath.getParentFile().exists()) {
                filePath.getParentFile().mkdirs();
            }
            //将上传文件保存到一个目标文件中
            myfile2.transferTo(filePath);
            //将重命名后的图片名存到goods对象中，添加时使用
            store.setCpicture(fileName);
        }
        Integer buser_id = MyUtil.getUser(session).getId();
        storeRepository.register(store.getSname(),store.getSpicture(),store.getCpicture(),buser_id);
        return "redirect:/";
    }

    @Override
    public String storeInfo(Store store, Model model, HttpSession session) {
        Integer buser_id = MyUtil.getUser(session).getId();
        List<Store> list = storeRepository.selectInfo(buser_id);
        if(list.size() > 0) {
            session.setAttribute("store", list.get(0));
        }
        model.addAttribute("evaluation",storeRepository.selectEva(list.get(0).getId()));
        return "store/storeInfo";
    }

    @Override
    public String selectMyGoods(Model model, int currentPage, String act, HttpSession session) {
        //共多少个商品
        Integer buser_id = MyUtil.getUser(session).getId();
        List<Store> list = storeRepository.selectInfo(buser_id);
        Integer store_id = list.get(0).getId();
        int totalCount = storeRepository.selectMyGoods(store_id);
        //计算共多少页
        int pageSize = 5;
        int totalPage = (int)Math.ceil(totalCount*1.0/pageSize);
        List<Goods> typeByPage = storeRepository.selectMyGoodsByPage((currentPage-1)*pageSize, pageSize, store_id);
        model.addAttribute("allGoods", typeByPage);
        model.addAttribute("totalPage", totalPage);
        model.addAttribute("currentPage", currentPage);
        model.addAttribute("act", act);
        return "store/selectMyGoods";
    }

    @Override
    public String selectOrder(Model model, int currentPage, HttpSession session) {
        //共多少个订单
        Integer buser_id = MyUtil.getUser(session).getId();
        List<Store> list = storeRepository.selectInfo(buser_id);
        Integer store_id = list.get(0).getId();
        int totalCount = storeRepository.selectAllOrder(store_id);
        //计算共多少页
        int pageSize = 5;
        int totalPage = (int)Math.ceil(totalCount*1.0/pageSize);
        List<Map<String, Object>> orderByPage = storeRepository.selectOrderByPage((currentPage-1)*pageSize, pageSize, store_id);
        model.addAttribute("allOrders", orderByPage);
        model.addAttribute("totalPage", totalPage);
        model.addAttribute("currentPage", currentPage);
        return "store/order";
    }
}
