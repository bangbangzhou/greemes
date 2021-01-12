## <center>目录</center>

- ### 1. 模块清单
  
  - [<h4 id="module-DB_REVERSE_MYSQL-from">1.1. _MYSQL</h4>](#module-DB_REVERSE_MYSQL "DB_REVERSE_MYSQL")
    - [<h5 id="module-DB_REVERSE_MYSQL-relation}-from">1.1.1. 关联关系</h5>](#module-DB_REVERSE_MYSQL-relation "关联关系")
    - [<h5 id="module-DB_REVERSE_MYSQL-tableList-from">1.1.2. 表清单</h5>](#module-DB_REVERSE_MYSQL-tableList "表清单")
    - [<h5 id="module-DB_REVERSE_MYSQL-tableColumnList-from">1.1.3. 表列清单</h5>](#module-DB_REVERSE_MYSQL-tableColumnList "表列清单")
      - [<h6 id="module-DB_REVERSE_MYSQL-tableColumnList-MES_ADMIN-from">1.1.3.1 MES\_ADMIN【后台用户表】</h6>](#module-DB_REVERSE_MYSQL-tableColumnList-MES_ADMIN "MES_ADMIN")
      - [<h6 id="module-DB_REVERSE_MYSQL-tableColumnList-MES_ADMIN_LOGIN_LOG-from">1.1.3.2 MES\_ADMIN\_LOGIN\_LOG【后台用户登录日志表】</h6>](#module-DB_REVERSE_MYSQL-tableColumnList-MES_ADMIN_LOGIN_LOG "MES_ADMIN_LOGIN_LOG")
      - [<h6 id="module-DB_REVERSE_MYSQL-tableColumnList-MES_ADMIN_ROLE_RELATION-from">1.1.3.3 MES\_ADMIN\_ROLE\_RELATION【后台用户和角色关系表】</h6>](#module-DB_REVERSE_MYSQL-tableColumnList-MES_ADMIN_ROLE_RELATION "MES_ADMIN_ROLE_RELATION")
      - [<h6 id="module-DB_REVERSE_MYSQL-tableColumnList-MES_BRAND-from">1.1.3.4 MES\_BRAND【品牌表】</h6>](#module-DB_REVERSE_MYSQL-tableColumnList-MES_BRAND "MES_BRAND")
      - [<h6 id="module-DB_REVERSE_MYSQL-tableColumnList-MES_MENU-from">1.1.3.5 MES\_MENU【后台菜单表】</h6>](#module-DB_REVERSE_MYSQL-tableColumnList-MES_MENU "MES_MENU")
      - [<h6 id="module-DB_REVERSE_MYSQL-tableColumnList-MES_RESOURCE-from">1.1.3.6 MES\_RESOURCE【后台资源表】</h6>](#module-DB_REVERSE_MYSQL-tableColumnList-MES_RESOURCE "MES_RESOURCE")
      - [<h6 id="module-DB_REVERSE_MYSQL-tableColumnList-MES_RESOURCE_CATEGORY-from">1.1.3.7 MES\_RESOURCE\_CATEGORY【资源分类表】</h6>](#module-DB_REVERSE_MYSQL-tableColumnList-MES_RESOURCE_CATEGORY "MES_RESOURCE_CATEGORY")
      - [<h6 id="module-DB_REVERSE_MYSQL-tableColumnList-MES_ROLE-from">1.1.3.8 MES\_ROLE【后台用户角色表】</h6>](#module-DB_REVERSE_MYSQL-tableColumnList-MES_ROLE "MES_ROLE")
      - [<h6 id="module-DB_REVERSE_MYSQL-tableColumnList-MES_ROLE_MENU_RELATION-from">1.1.3.9 MES\_ROLE\_MENU\_RELATION【后台角色菜单关系表】</h6>](#module-DB_REVERSE_MYSQL-tableColumnList-MES_ROLE_MENU_RELATION "MES_ROLE_MENU_RELATION")
      - [<h6 id="module-DB_REVERSE_MYSQL-tableColumnList-MES_ROLE_RESOURCE_RELATION-from">1.1.3.10 MES\_ROLE\_RESOURCE\_RELATION【后台角色资源关系表】</h6>](#module-DB_REVERSE_MYSQL-tableColumnList-MES_ROLE_RESOURCE_RELATION "MES_ROLE_RESOURCE_RELATION")
  
  ---

### 1. 模块清单

 ---

- [<h5 id="module-DB_REVERSE_MYSQL-tableList">1.1.2 表清单</h5>](#module-DB_REVERSE_MYSQL-tableList-from)
  
  ---

| 名称         | 代码                            | 备注  |
| ---------- | ----------------------------- | --- |
| 后台用户表      | MES\_ADMIN                    |     |
| 后台用户登录日志表  | MES\_ADMIN\_LOGIN\_LOG        |     |
| 后台用户和角色关系表 | MES\_ADMIN\_ROLE\_RELATION    |     |
| 品牌表        | MES\_BRAND                    |     |
| 后台菜单表      | MES\_MENU                     |     |
| 后台资源表      | MES\_RESOURCE                 |     |
| 资源分类表      | MES\_RESOURCE\_CATEGORY       |     |
| 后台用户角色表    | MES\_ROLE                     |     |
| 后台角色菜单关系表  | MES\_ROLE\_MENU\_RELATION     |     |
| 后台角色资源关系表  | MES\_ROLE\_RESOURCE\_RELATION |     |

 ---

- [<h5 id="module-DB_REVERSE_MYSQL-tableColumnList">1.1.3 表列清单</h5>](#module-DB_REVERSE_MYSQL-tableColumnList-from)
  
  ---

- [<h6 id="module-DB_REVERSE_MYSQL-tableColumnList-MES_ADMIN">MES_ADMIN【后台用户表】</h6>](#module-DB_REVERSE_MYSQL-tableColumnList-MES_ADMIN-from)

| 代码           | 名称                 | 数据类型(MYSQL)  | 主键  | 备注  |
| ------------ | ------------------ | ------------ | --- | --- |
| ID           |                    | BIGINT(19)   | √   |     |
| USERNAME     |                    | VARCHAR(64)  |     |     |
| PASSWORD     |                    | VARCHAR(64)  |     |     |
| ICON         | 头像                 | VARCHAR(500) |     |     |
| EMAIL        | 邮箱                 | VARCHAR(100) |     |     |
| NICK\_NAME   | 昵称                 | VARCHAR(200) |     |     |
| NOTE         | 备注信息               | VARCHAR(500) |     |     |
| CREATE\_TIME | 创建时间               | DATETIME     |     |     |
| LOGIN\_TIME  | 最后登录时间             | DATETIME     |     |     |
| STATUS       | 帐号启用状态：0->禁用；1->启用 | INT(10)      |     |     |

 ---

- [<h6 id="module-DB_REVERSE_MYSQL-tableColumnList-MES_ADMIN_LOGIN_LOG">MES_ADMIN_LOGIN_LOG【后台用户登录日志表】</h6>](#module-DB_REVERSE_MYSQL-tableColumnList-MES_ADMIN_LOGIN_LOG-from)

| 代码           | 名称      | 数据类型(MYSQL)  | 主键  | 备注  |
| ------------ | ------- | ------------ | --- | --- |
| ID           |         | BIGINT(19)   | √   |     |
| ADMIN\_ID    |         | BIGINT(19)   |     |     |
| CREATE\_TIME |         | DATETIME     |     |     |
| IP           |         | VARCHAR(64)  |     |     |
| ADDRESS      |         | VARCHAR(100) |     |     |
| USER\_AGENT  | 浏览器登录类型 | VARCHAR(100) |     |     |

 ---

- [<h6 id="module-DB_REVERSE_MYSQL-tableColumnList-MES_ADMIN_ROLE_RELATION">MES_ADMIN_ROLE_RELATION【后台用户和角色关系表】</h6>](#module-DB_REVERSE_MYSQL-tableColumnList-MES_ADMIN_ROLE_RELATION-from)

| 代码        | 名称  | 数据类型(MYSQL) | 主键  | 备注  |
| --------- | --- | ----------- | --- | --- |
| ID        |     | BIGINT(19)  | √   |     |
| ADMIN\_ID |     | BIGINT(19)  |     |     |
| ROLE\_ID  |     | BIGINT(19)  |     |     |

 ---

- [<h6 id="module-DB_REVERSE_MYSQL-tableColumnList-MES_BRAND">MES_BRAND【品牌表】</h6>](#module-DB_REVERSE_MYSQL-tableColumnList-MES_BRAND-from)

| 代码                      | 名称                  | 数据类型(MYSQL)  | 主键  | 备注  |
| ----------------------- | ------------------- | ------------ | --- | --- |
| ID                      |                     | BIGINT(19)   | √   |     |
| NAME                    |                     | VARCHAR(64)  |     |     |
| FIRST\_LETTER           | 首字母                 | VARCHAR(8)   |     |     |
| SORT                    |                     | INT(10)      |     |     |
| FACTORY\_STATUS         | 是否为品牌制造商：0->不是；1->是 | INT(10)      |     |     |
| SHOW\_STATUS            |                     | INT(10)      |     |     |
| PRODUCT\_COUNT          | 产品数量                | INT(10)      |     |     |
| PRODUCT\_COMMENT\_COUNT | 产品评论数量              | INT(10)      |     |     |
| LOGO                    | 品牌logo              | VARCHAR(255) |     |     |
| BIG\_PIC                | 专区大图                | VARCHAR(255) |     |     |
| BRAND\_STORY            | 品牌故事                | TEXT         |     |     |

 ---

- [<h6 id="module-DB_REVERSE_MYSQL-tableColumnList-MES_MENU">MES_MENU【后台菜单表】</h6>](#module-DB_REVERSE_MYSQL-tableColumnList-MES_MENU-from)

| 代码           | 名称   | 数据类型(MYSQL)  | 主键  | 备注  |
| ------------ | ---- | ------------ | --- | --- |
| ID           |      | BIGINT(19)   | √   |     |
| PARENT\_ID   | 父级ID | BIGINT(19)   |     |     |
| CREATE\_TIME | 创建时间 | DATETIME     |     |     |
| TITLE        | 菜单名称 | VARCHAR(100) |     |     |
| LEVEL        | 菜单级数 | INT(10)      |     |     |
| SORT         | 菜单排序 | INT(10)      |     |     |
| NAME         | 前端名称 | VARCHAR(100) |     |     |
| ICON         | 前端图标 | VARCHAR(200) |     |     |
| HIDDEN       | 前端隐藏 | INT(10)      |     |     |

 ---

- [<h6 id="module-DB_REVERSE_MYSQL-tableColumnList-MES_RESOURCE">MES_RESOURCE【后台资源表】</h6>](#module-DB_REVERSE_MYSQL-tableColumnList-MES_RESOURCE-from)

| 代码           | 名称     | 数据类型(MYSQL)  | 主键  | 备注  |
| ------------ | ------ | ------------ | --- | --- |
| ID           |        | BIGINT(19)   | √   |     |
| CREATE\_TIME | 创建时间   | DATETIME     |     |     |
| NAME         | 资源名称   | VARCHAR(200) |     |     |
| URL          | 资源URL  | VARCHAR(200) |     |     |
| DESCRIPTION  | 描述     | VARCHAR(500) |     |     |
| CATEGORY\_ID | 资源分类ID | BIGINT(19)   |     |     |

 ---

- [<h6 id="module-DB_REVERSE_MYSQL-tableColumnList-MES_RESOURCE_CATEGORY">MES_RESOURCE_CATEGORY【资源分类表】</h6>](#module-DB_REVERSE_MYSQL-tableColumnList-MES_RESOURCE_CATEGORY-from)

| 代码           | 名称   | 数据类型(MYSQL)  | 主键  | 备注  |
| ------------ | ---- | ------------ | --- | --- |
| ID           |      | BIGINT(19)   | √   |     |
| CREATE\_TIME | 创建时间 | DATETIME     |     |     |
| NAME         | 分类名称 | VARCHAR(200) |     |     |
| SORT         | 排序   | INT(10)      |     |     |

 ---

- [<h6 id="module-DB_REVERSE_MYSQL-tableColumnList-MES_ROLE">MES_ROLE【后台用户角色表】</h6>](#module-DB_REVERSE_MYSQL-tableColumnList-MES_ROLE-from)

| 代码           | 名称               | 数据类型(MYSQL)  | 主键  | 备注  |
| ------------ | ---------------- | ------------ | --- | --- |
| ID           |                  | BIGINT(19)   | √   |     |
| NAME         | 名称               | VARCHAR(100) |     |     |
| DESCRIPTION  | 描述               | VARCHAR(500) |     |     |
| ADMIN\_COUNT | 后台用户数量           | INT(10)      |     |     |
| CREATE\_TIME | 创建时间             | DATETIME     |     |     |
| STATUS       | 启用状态：0->禁用；1->启用 | INT(10)      |     |     |
| SORT         |                  | INT(10)      |     |     |

 ---

- [<h6 id="module-DB_REVERSE_MYSQL-tableColumnList-MES_ROLE_MENU_RELATION">MES_ROLE_MENU_RELATION【后台角色菜单关系表】</h6>](#module-DB_REVERSE_MYSQL-tableColumnList-MES_ROLE_MENU_RELATION-from)

| 代码       | 名称   | 数据类型(MYSQL) | 主键  | 备注  |
| -------- | ---- | ----------- | --- | --- |
| ID       |      | BIGINT(19)  | √   |     |
| ROLE\_ID | 角色ID | BIGINT(19)  |     |     |
| MENU\_ID | 菜单ID | BIGINT(19)  |     |     |

 ---

- [<h6 id="module-DB_REVERSE_MYSQL-tableColumnList-MES_ROLE_RESOURCE_RELATION">MES_ROLE_RESOURCE_RELATION【后台角色资源关系表】</h6>](#module-DB_REVERSE_MYSQL-tableColumnList-MES_ROLE_RESOURCE_RELATION-from)

| 代码           | 名称   | 数据类型(MYSQL) | 主键  | 备注  |
| ------------ | ---- | ----------- | --- | --- |
| ID           |      | BIGINT(19)  | √   |     |
| ROLE\_ID     | 角色ID | BIGINT(19)  |     |     |
| RESOURCE\_ID | 资源ID | BIGINT(19)  |     |     |

 ---
