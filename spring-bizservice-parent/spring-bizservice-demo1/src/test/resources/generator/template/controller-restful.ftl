package ${basePackage}.controller;

import ${basePackage}.core.Result;
import ${basePackage}.core.ResultGenerator;
import ${basePackage}.model.${modelNameUpperCamel};
import ${basePackage}.service.${modelNameUpperCamel}Service;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
* Created by ${author} on ${date}.
*/
@Controller
@RequestMapping("${baseRequestMapping}")
public class ${modelNameUpperCamel}Controller {
    @Resource
    private ${modelNameUpperCamel}Service ${modelNameLowerCamel}Service;

    /**
    * 进入添加页面
    */
    @RequestMapping(value = "/toSave", method = RequestMethod.GET)
    public String toSave(Model model) {
        model.addAttribute("pageMold","save");
        return "${modelNameUnderlineCamel}/add";
    }

    /**
    * 进入修改页面
    */
    @RequestMapping(value = "/toEdit/{id}", method = RequestMethod.GET)
    public String toEdit(Model model,@PathVariable Integer id) {
    ${modelNameUpperCamel} ${modelNameLowerCamel} = ${modelNameLowerCamel}Service.findById(id);

    model.addAttribute("${modelNameLowerCamel}", ${modelNameLowerCamel});
    model.addAttribute("pageMold","edit");
        return "${modelNameUnderlineCamel}/update";
    }

    /**
    * AJAX 新增 接口
    */
    @RequestMapping(value = "/save", method = RequestMethod.POST)
    @ResponseBody
    public Result add(${modelNameUpperCamel} ${modelNameLowerCamel}) {
        ${modelNameLowerCamel}Service.save(${modelNameLowerCamel});
        return ResultGenerator.genSuccessResult();
    }

    /**
    * 删除
    */
    @RequestMapping(value = "/del/{id}", method = RequestMethod.POST)
    @ResponseBody
    public Result delete(@PathVariable Integer id) {
        ${modelNameLowerCamel}Service.deleteById(id);
        return ResultGenerator.genSuccessResult();
    }

    /**
    * AJAX 修改 接口
    */
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    @ResponseBody
    public Result update(${modelNameUpperCamel} ${modelNameLowerCamel}) {
        ${modelNameLowerCamel}Service.update(${modelNameLowerCamel});
        return ResultGenerator.genSuccessResult();
    }

    /**
    * 查询
    */
    @RequestMapping("/list")
    public String list(Model model,@RequestParam(defaultValue = "1") Integer pageNum, @RequestParam(defaultValue = "10") Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        List<${modelNameUpperCamel}> list = ${modelNameLowerCamel}Service.findAll();
        PageInfo pageInfo = new PageInfo(list);
        model.addAttribute("data", pageInfo);
        return "${modelNameUnderlineCamel}/list";
    }
}
