package com.deltagis.success.domain.common.port;

import java.io.Serializable;

public interface PrimaryKeyObject extends Serializable {
    /**
     * @return internal unique ID of the object as used in the database
     */
    long getId();

    /**
     * @return external unique ID of the object as used in the RESTful API
     */
    String getUid();
}