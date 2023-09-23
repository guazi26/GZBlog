package org.guazi26.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.guazi26.constant.SystemConstants;
import org.guazi26.dao.CommentDao;
import org.guazi26.domain.ResponseResult;
import org.guazi26.domain.entity.Comment;
import org.guazi26.domain.entity.SysUser;
import org.guazi26.domain.vo.CommentVo;
import org.guazi26.domain.vo.PageVo;
import org.guazi26.service.CommentService;
import org.guazi26.service.SysUserService;
import org.guazi26.utils.BeanCopyUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 评论表(Comment)表服务实现类
 *
 * @author makejava
 * @since 2023-09-22 03:26:06
 */
@Service("commentService")
public class CommentServiceImpl extends ServiceImpl<CommentDao, Comment> implements CommentService {
    @Autowired
    private SysUserService sysUserService;


    /**
     * 添加新评论
     *
     * @param comment
     * @return
     */
    @Override
    public ResponseResult addComment(Comment comment) {
        comment.setCreateBy(1L);

        save(comment);
        return ResponseResult.okResult();
    }

    /**
     * 查询评论
     *
     * @param commentType
     * @param articleId
     * @param pageNum
     * @param pageSize
     * @return
     */
    @Override
    public ResponseResult commentList(String commentType, Long articleId, Integer pageNum, Integer pageSize) {
        //查询根评论:
        LambdaQueryWrapper<Comment> queryWrapper = new LambdaQueryWrapper<>();

        //评论类型
        queryWrapper.eq(Comment::getType, commentType);

        //对articleId进行判断
        queryWrapper.eq(SystemConstants.ARTICLE_COMMENT.equals(commentType),
                Comment::getArticleId, articleId);

        //根评论rootId为-1
        queryWrapper.eq(Comment::getRootId, SystemConstants.ROOT_ID);


        //分页查询
        Page<Comment> page = new Page(pageNum, pageSize);
        page(page, queryWrapper);

        //根评论集合
        List<Comment> comments = page.getRecords();
        List<CommentVo> commentVoList = toCommenVoList(comments);

        //查询根评论下面的子评论
        for (CommentVo commentVo : commentVoList) {
            //查询对应的子评论(comment表中rootId == id)
            LambdaQueryWrapper<Comment> wrapper = new LambdaQueryWrapper<>();
            wrapper.eq(Comment::getRootId, commentVo.getId());
            //按照时间对子评论排序
            wrapper.orderByAsc(Comment::getCreateTime);
            List<Comment> list = list(wrapper);

            List<CommentVo> commentVos = toCommenVoList(list);

            commentVo.setChildren(commentVos);
        }
        return ResponseResult.okResult(new PageVo(commentVoList, page.getTotal()));


    }


    /**
     * 补充评论vo缺失字段
     *
     * @param commentList
     * @return
     */
    private List<CommentVo> toCommenVoList(List<Comment> commentList) {
        List<CommentVo> commentVos = BeanCopyUtils.copyBeanList(commentList, CommentVo.class);
        //遍历commentVo集合
        for (CommentVo commentVo : commentVos) {
            if (commentVo.getToCommentUserId() != -1) {
                SysUser ToUser = sysUserService.getById(commentVo.getToCommentUserId());
                commentVo.setToCommentUserName(ToUser.getNickName());
            }
            SysUser User = sysUserService.getById(commentVo.getCreateBy());
            commentVo.setUsername(User.getNickName());
        }
        return commentVos;
    }
}
