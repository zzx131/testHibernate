1,实体状态有三种
1）瞬时态
对象里面没有id，没有与session进行关联
2）持久态
对象里面有id值，对象与session有关联
3）托管态
对象里面有id值，没有与session关联
2，Hibernate的一级缓存
什么是缓存
1)，数据存放到数据库里面，数据库本身是文件系统，使用流方式操作文件效率不是很高
2),吧数据放到内存中，提供读取效率

Hibernate缓存
1，hibernate的一级缓存
   (1）hibernate的一级缓存默认是打开的
   (2) hibernate的使用的范围是，是session的范围从session创建到session关闭范围
   (3) hibernate的一级缓存中，存储数据是必须的持久数据。

   一级缓存的特性
   1，持久态自动更新，不需要update
2，hibernate的二级缓存
    （1）目前不在使用，替代技术是redis
    （2）二级缓存默认不是打开的，需要配置
    （3）二级缓存的使用范围是，sessionFaction的范围
3，验证一级缓存存在
    （1）首先根据uid=1查询，返回数据
    （2）第二查询的时候从缓存中进行查询

Hibernate绑定session
1，帮我们实现本地线程绑定session
2，获取与本地线程session
1）在hibernate核心配置文件中添加配置
2）用hibernate中有一个方法进行调用



Hibernate的api使用

1，Query对象
    不需要写sql语句但是的写hql语句
    查询所有的hql语句
    （1）from 实体对象名称
（1）创建Query对象
（2）写hql语句
2，Criteria对象
    1),使用这个对象查询操作，但是使用这个对象的时候，不需要写语句，直接调用方法就可以了
    2),创建对象Criteria对象
3，SQLQuery对象
    1,使用hibernate时候，调用底层sql实现。

Hibernate中的数据库中TimeStamp，设定默认值为CURRENT_TIMESTAMP不起作用？
原因是，在save的过程中添加了所有的字段，这时的时间默认是为null，假如进行手动设置为NULL不会插入当前时间，
解决办法是，设置当前时间