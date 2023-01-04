package team.globaloptima;

import javax.enterprise.context.RequestScoped;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;

@RequestScoped
public class SupplierService {

    @PersistenceContext(unitName = "team-globaloptima-supplier")
    private EntityManager em;

    public Supplier getSupplier(Integer supplierId) {
        return em.find(Supplier.class, supplierId);
    }

    public List<Supplier> getSuppliers() {
        List<Supplier> suppliers = em
                .createNamedQuery("Supplier.findSuppliers", Supplier.class)
                .getResultList();

        return suppliers;
    }

    @Transactional
    public void saveSupplier(Supplier supplier) {
        if (supplier != null) {
            em.persist(supplier);
        }

    }

    @Transactional(Transactional.TxType.REQUIRED)
    public void deleteSupplier(Integer supplierId) {
        Supplier supplier = em.find(Supplier.class, supplierId);
        if (supplier != null) {
            em.remove(supplier);
        }
    }

    public List<Food> getMenu(Integer supplierId) {
        List<Food> menu = em
                .createQuery("SELECT f FROM Food f WHERE f.supplierId = :suppId")
                .setParameter("suppId", supplierId)
                .getResultList();
        return menu;
    }

    @Transactional
    public void saveFood(Food food) {
        if (food != null) {
            em.persist(food);
        }

    }

}
