package com.buct.spider.service;

import com.buct.spider.entity.Codeforces;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author BUCT
 * @since 2022-06-16
 */
public interface ICodeforcesService extends IService<Codeforces> {

    /**
     * 爬取Codeforces比赛数据
     */
    void codeforcesCrawling();
}
