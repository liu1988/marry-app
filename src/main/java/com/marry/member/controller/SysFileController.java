package com.marry.member.controller;

import com.marry.common.domain.request.SysFile;
import com.marry.common.domain.response.Result;
import com.marry.common.domain.response.ResultCode;
import com.marry.member.service.SysFileService;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

@RestController
@RequestMapping("/file")
@Api(value = "文件管理", description = "文件系统的上传等接口")
public class SysFileController {
    @Value("${file.path}")
    private String filePath;

    @Value("${file.url}")
    private String fileUrl;

    @Autowired
    private SysFileService sysFileService;


    @PostMapping("/upload")
    @ApiOperation(value = "文件上传", response = Result.class)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "file", value = "文件", required = true),
            @ApiImplicitParam(name = "sysFile", value = "文件信息，需要带上会员ID,是否是用户头像，文件上传框的次序", required = true, paramType = "String")
    })
    public Result upload(@RequestParam("file") MultipartFile multipartFile, SysFile sysFile) {
        try {
            if (multipartFile.isEmpty()) {
                return new Result(ResultCode.FILE_HANDLE_FAILURE.getCode(), ResultCode.FILE_HANDLE_FAILURE.getMessage(), null);
            }
            //video/mp4
            String contentType = multipartFile.getContentType();
            String realFileName = multipartFile.getOriginalFilename();

            if (contentType.contains("video")) {
                if (!contentType.contains("mp4")) {
                    return new Result(ResultCode.Video_HANDLE_FAILURE.getCode(), ResultCode.Video_HANDLE_FAILURE.getMessage(), null);
                }
            }
            //生成多级文件夹
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM");
            String[] split = format.format(new Date()).split("-");
            StringBuffer stringBuffer = new StringBuffer();
            for (int i = 0; i < split.length; i++) {
                String s = split[i];
                stringBuffer.append(s);
                stringBuffer.append("/");
            }
            String suffix = realFileName.substring(realFileName.lastIndexOf("."), realFileName.length());
            sysFile.setFileName(System.currentTimeMillis() + suffix);

            //上传文件
            File file = new File(filePath + stringBuffer.toString() + sysFile.getFileName());
            if (!file.exists()) {
                File parentFile = file.getParentFile();
                if(!parentFile.exists()){
                    parentFile.mkdirs();
                }
                multipartFile.transferTo(file);
            }

            if (contentType.contains("video")) {
                sysFile.setType("1");
            } else if (contentType.contains("image")) {
                sysFile.setType("2");
            } else {
                sysFile.setType("3");
            }
            sysFile.setFileRealName(realFileName);
            sysFile.setContentType(contentType);

            sysFile.setFileUrl(fileUrl + stringBuffer.toString() + sysFile.getFileName());
            //保存文件信息
            SysFile save = sysFileService.save(sysFile);
            if(save!=null){
                return new Result(ResultCode.SUCCESS.getCode(), ResultCode.SUCCESS.getMessage(), save);
            }else{
                return new Result(ResultCode.FAILURE.getCode(), ResultCode.FAILURE.getMessage(), null);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return new Result(ResultCode.FILE_HANDLE_FAILURE.getCode(), ResultCode.FILE_HANDLE_FAILURE.getMessage(), null);
    }

    /**
     * 删除文件
     *
     * @param id
     * @return
     */
    @GetMapping("/delete")
    @ApiOperation(value = "删除文件", response = Result.class)
    @ApiParam(name = "id", value = "文件id", required = true)
    public Result deleteFile(@RequestParam("id") Integer id) {
        try {
            boolean bool = sysFileService.deleteSysFileById(id);
            if(bool){
                return new Result(ResultCode.SUCCESS.getCode(), ResultCode.SUCCESS.getMessage(), true);
            }else {
                return new Result(ResultCode.FAILURE.getCode(), ResultCode.FAILURE.getMessage(), false);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return new Result(ResultCode.FILE_HANDLE_FAILURE.getCode(), ResultCode.FILE_HANDLE_FAILURE.getMessage(), null);
    }

}
