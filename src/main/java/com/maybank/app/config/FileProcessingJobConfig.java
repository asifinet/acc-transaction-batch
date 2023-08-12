package com.maybank.app.config;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.mapping.PassThroughLineMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;

import com.maybank.app.model.Transaction;
import com.maybank.app.processor.TransactionItemProcessor;
import com.maybank.app.repository.TransactionRepository;

@Configuration
public class FileProcessingJobConfig {

    @Autowired
    private JobBuilderFactory jobBuilderFactory;

    @Autowired
    private StepBuilderFactory stepBuilderFactory;

    @Autowired TransactionRepository transactionrepository;
    
    @Bean
    public Job fileProcessingJob() {
        return jobBuilderFactory.get("fileProcessingJob")
                .incrementer(new RunIdIncrementer())
                .start(processFileStep())
                .build();
    }

    @Bean
    public Step processFileStep() {
        return stepBuilderFactory.get("processFileStep")
                .<String, Transaction>chunk(10)
                .reader(fileItemReader())
                .processor(transactionItemProcessor())
                .writer(transactionItemWriter())
                .build();
    }

    @Bean
    public FlatFileItemReader<String> fileItemReader() {
        FlatFileItemReader<String> reader = new FlatFileItemReader<>();
        reader.setResource(new ClassPathResource("dataSource.txt")); // Replace with your file path
        reader.setLineMapper(new PassThroughLineMapper()); // Reads lines as-is
        return reader;
    }

    @Bean
    public ItemProcessor<String, Transaction> transactionItemProcessor() {
        return new TransactionItemProcessor();
    }

    @Bean
    public ItemWriter<Transaction> transactionItemWriter() {
    	  return items -> {
            for (Transaction transaction : items) {
                System.out.println("Processed: " + transaction);
                transactionrepository.save(transaction);
            }
        };
        
    }
}
