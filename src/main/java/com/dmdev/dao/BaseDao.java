package com.dmdev.dao;

import com.dmdev.entity.BaseEntityInterface;

import java.io.Serializable;

public abstract class BaseDao<K extends Serializable, E extends BaseEntityInterface<K>>  implements Dao<K, E> {


}
