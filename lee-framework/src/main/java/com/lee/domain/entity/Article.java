package com.lee.domain.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import java.util.Date;

/**
 * 文章表(Article)表实体类
 *
 * @author makejava
 * @since 2022-04-02 11:35:17
 */
@SuppressWarnings("serial")
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("sg_article")
@Accessors(chain = true)
@Document(indexName = "blog", type = "article", shards = 1, replicas = 0)
@ApiModel
public class Article {
    private static final long serialVersionUID = 884140206313067298L;

    @TableId
    @Id
    @ApiModelProperty("文章ID")
    private Long id;
    /**
     * 标题
     * es:可根据ik分词器查找
     */
    @Field(analyzer = "ik_max_word", type = FieldType.Text)
    @ApiModelProperty("文章标题")
    private String title;
    /**
     * 文章内容
     */
    @Field(analyzer = "ik_max_word", type = FieldType.Text)
    @ApiModelProperty("文章内容")
    private String content;
    /**
     * 文章摘要
     */
    @Field(analyzer = "ik_max_word", type = FieldType.Text)
    @ApiModelProperty("文章摘要")
    private String summary;
    /**
     * 所属分类id
     */
    @ApiModelProperty("文章分类id")
    private Long categoryId;
    /**
     * 添加分类Name
     * es：根据分类名称关键字查找
     */
    @TableField(exist = false)
    @Field(type = FieldType.Keyword)
    @ApiModelProperty("文章分类")
    private String categoryName;

    /**
     * 文章标签
     */
    @Field(analyzer = "ik_max_word", type = FieldType.Text)
    @ApiModelProperty("文章标签")
    private String articleTags;

    /**
     * 缩略图
     */
    @ApiModelProperty("文章缩略图")
    private String thumbnail;
    /**
     * 是否置顶（0否，1是）
     */
    @ApiModelProperty("是否置顶（0否，1是）")
    private String isTop;
    /**
     * 状态（0已发布，1草稿）
     */
    @ApiModelProperty("状态（0已发布，1草稿）")
    private String status;
    /**
     * 访问量
     */
    @ApiModelProperty("访问量")
    private Long viewCount;
    /**
     * 是否允许评论 1是，0否
     */
    @ApiModelProperty("是否允许评论 1是，0否")
    private String isComment;

    @ApiModelProperty("文章创建者")
    private String creatorName;

    @TableField(fill = FieldFill.INSERT)
    @ApiModelProperty("文章创建者id")
    private Long createBy;

    @TableField(fill = FieldFill.INSERT)
    @ApiModelProperty("文章创建时间")
    private Date createTime;

    @TableField(fill = FieldFill.UPDATE)
    @ApiModelProperty("文章更新者")
    private Long updateBy;

    @TableField(fill = FieldFill.UPDATE)
    @ApiModelProperty("文章更新时间")
    private Date updateTime;
    /**
     * 删除标志（0代表未删除，1代表已删除）
     */
    @ApiModelProperty("文章是否删除")
    private Integer delFlag;

    public Article(Long id, Long viewCount) {
        this.id = id;
        this.viewCount = viewCount;
    }
}

