package az.itbtechno.ecommerce.services.impls;

import az.itbtechno.ecommerce.dto.response.category.CategoryHomeDTO;
import az.itbtechno.ecommerce.models.Category;
import az.itbtechno.ecommerce.repostories.CategoryRepository;
import az.itbtechno.ecommerce.services.CategoryService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.kafka.core.KafkaTemplate; // Kafka üçün
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;
    private final ModelMapper modelMapper;
    private static final String TOPIC = "test-topic";
    private final KafkaTemplate<String, String> kafkaTemplate;

    @Override
    public List<CategoryHomeDTO> categoryList() {
        kafkaTemplate.send(TOPIC, "Salam mesaji gordunse her sey isleyir");
        System.out.println("Message sent to Kafka topic '" + TOPIC + "': Salam mesaji gordunse her sey isleyir");
        List<Category> categories = categoryRepository.findAll();
        List<CategoryHomeDTO> categoryHomeDTOList = categories.stream()
                .map(category -> modelMapper.map(category, CategoryHomeDTO.class))
                .toList();

        return categoryHomeDTOList;
    }
}