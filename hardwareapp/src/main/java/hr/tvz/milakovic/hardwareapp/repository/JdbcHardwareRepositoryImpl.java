package hr.tvz.milakovic.hardwareapp.repository;

import hr.tvz.milakovic.hardwareapp.entity.Hardware;
import hr.tvz.milakovic.hardwareapp.entity.HardwareType;
import org.springframework.context.annotation.Primary;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

@Primary
@Repository
public class JdbcHardwareRepositoryImpl implements HardwareRepository {

    private static final String SELECT_ALL =
            "SELECT id, code, name, price, type, available FROM hardware";

    private final JdbcTemplate jdbc;
    private final SimpleJdbcInsert inserter;

    public JdbcHardwareRepositoryImpl(JdbcTemplate jdbc) {
        this.jdbc = jdbc;
        this.inserter = new SimpleJdbcInsert(jdbc)
                .withTableName("hardware")
                .usingGeneratedKeyColumns("id");
    }

    @Override
    public List<Hardware> findAll() {
        return jdbc.query(SELECT_ALL, this::mapRowToHardware);
    }

    @Override
    public Optional<Hardware> findByCode(String code) {
        try {
            return Optional.ofNullable(
                    jdbc.queryForObject(SELECT_ALL + " WHERE code = ? ", this::mapRowToHardware, code)
            );
        } catch (EmptyResultDataAccessException e) {
            return Optional.empty();
        }
    }

    @Override
    public Optional<Hardware> save(Hardware hardware) {
        try {
            hardware.setId(saveHardwareDetails(hardware));
            return Optional.of(hardware);
        } catch (DuplicateKeyException e) {
            return Optional.empty();
        }
    }

    private Long saveHardwareDetails(Hardware hardware){
        Map<String, Object> values = new HashMap<>();

        values.put("code", hardware.getCode());
        values.put("name", hardware.getName());
        values.put("price", hardware.getPrice());
        values.put("type", hardware.getType());
        values.put("available", hardware.getAvailable());

        return (Long)inserter.executeAndReturnKey(values);
    }

    @Override
    public Optional<Hardware> update(Long id, Hardware updatedHardware) {
        int executed = jdbc.update("UPDATE hardware set " +
                "code = ?, " +
                "name = ?, " +
                "price = ?, " +
                "type = ?, " +
                "available = ? " +
                " WHERE id = ?",
                updatedHardware.getCode(),
                updatedHardware.getName(),
                updatedHardware.getPrice(),
                updatedHardware.getType().toString(),
                updatedHardware.getAvailable(),
                updatedHardware.getId()
        );

        if(executed > 0){
            return Optional.of(updatedHardware);
        } else {
            return Optional.empty();
        }
    }

    @Override
    public void deleteByCode(String code) {
        jdbc.update( "DELETE FROM hardware WHERE code = ?", code);
    }

    private Hardware mapRowToHardware(ResultSet rs, int rowNum) throws SQLException {
        HardwareType tempType = HardwareType.valueOf(rs.getString("type"));

        return new Hardware(
                rs.getLong("id"),
                rs.getString("code"),
                rs.getString("name"),
                rs.getDouble("price"),
                tempType,
                rs.getInt("available")
        );
    }
}
