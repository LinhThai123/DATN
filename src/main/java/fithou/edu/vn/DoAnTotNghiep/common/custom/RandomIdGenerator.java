package fithou.edu.vn.DoAnTotNghiep.common.custom;

import org.hibernate.HibernateException;
import org.hibernate.engine.spi.SharedSessionContractImplementor;
import org.hibernate.id.IdentifierGenerator;

import java.io.Serializable;

public class RandomIdGenerator implements IdentifierGenerator {
    @Override
    public Serializable generate(SharedSessionContractImplementor session, Object object) throws HibernateException {
        RandomString randomString = new RandomString( 16 );
        return randomString.nextString();
    }
}
