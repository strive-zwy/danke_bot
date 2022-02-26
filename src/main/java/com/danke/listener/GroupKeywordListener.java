package com.danke.listener;

import com.danke.entity.GroupInfo;
import com.danke.entity.Keyword;
import com.danke.mapper.GroupInfoMapper;
import com.danke.mapper.KeywordMapper;
import com.danke.utils.StrFenciUtils;
import love.forte.simbot.annotation.Filter;
import love.forte.simbot.annotation.FilterValue;
import love.forte.simbot.annotation.ListenBreak;
import love.forte.simbot.annotation.OnGroup;
import love.forte.simbot.api.message.events.GroupMsg;
import love.forte.simbot.api.sender.MsgSender;
import love.forte.simbot.filter.MatchType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author zwy
 * @version 1.0
 * @createTime 2021/11/27 16:51
 * @description: 群监控-关键词监听
 */
@Service
public class GroupKeywordListener {

    @Qualifier("keywordMapper")
    @Autowired
    private KeywordMapper keywordMapper;

    @Qualifier("groupInfoMapper")
    @Autowired
    private GroupInfoMapper groupInfoMapper;

    /*
     * 关键词监听
     * */
    @OnGroup
    public void keyword(GroupMsg msg, MsgSender sender) throws Exception {
        GroupInfo g = groupInfoMapper.findOne(
                groupInfoMapper.query().where.groupNumber()
                        .eq(msg.getGroupInfo().getGroupCodeNumber()).end()
        );
        if (g.getIsMonitor() == 1) {
            List<Keyword> kl = keywordMapper.listEntity(
                    keywordMapper.query().where().groupId().eq(g.getId())
                            .state().eq(1).end()
            );
            StringBuilder kwStr = new StringBuilder("keywordList");
            for (Keyword keyword : kl) {
                kwStr.append("-").append(keyword);
            }
            String kw = kwStr.toString();
            List<String> strs = StrFenciUtils.fenci(msg.getText());
            for (String s : strs) {
                if (kw.contains(s)) {
                    sender.SETTER.setMsgRecall(msg.getFlag());
                    sender.SENDER.sendGroupMsg(msg,"该群内禁止发送此类消息！");
                    return;
                }
            }
        }

    }

}
