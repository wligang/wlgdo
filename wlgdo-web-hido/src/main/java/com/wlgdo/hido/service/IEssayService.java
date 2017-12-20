package com.wlgdo.hido.service;

import java.util.List;

import com.wlgdo.hido.domain.CommentPo;
import com.wlgdo.hido.domain.EssayPo;

/**
 * @author wlg
 *
 */

public interface IEssayService {

    EssayPo saveEssay(EssayPo esaay);

    /**
     * 查询所有的文章
     * @author wlg
     * 2016年12月10日
     * @return
     */
    List<EssayPo> queryEssayList(EssayPo essay);

    /**
     * @param comment
     * @author wlg
     * 2016年12月10日
     * @return 
     * int
     */
    int saveComment(CommentPo comment);

    /**
     * 2016年12月10日
     * @author wlg
     * @param commnet
     * @return  
     * List<CommentPo>
     */
    List<CommentPo> queryCommentList(CommentPo commnet);

    /**
     * 根据用户uid查找文章动态
     * @author wlgdo[wlgchun@163.com] 2016年12月31日 
     * @param attribute void
     * @return 
     */
    List<EssayPo> queryEssayListByid(String uid, boolean isopen);

    /**
     * 赞操作
     * @author wlgdo[wlgchun@163.com] 2017年1月7日 
     * @param essayId 动态ID
     * @param uid 用户id
     * @return int 赞的个数
     */
    int saveEssayZan(String essayId, String uid);

    /**
     * 
     * @author wlgdo[wlgchun@163.com] 2017年1月7日 
     * @param context
     * @param essayId
     * @param uid
     * @return int
     */
    int saveComent(String context, String essayId, String uid);

}
