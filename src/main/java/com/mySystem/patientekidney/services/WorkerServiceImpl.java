package com.mySystem.patientekidney.services;

import com.mySystem.patientekidney.models.entities.Worker;
import com.mySystem.patientekidney.models.repositories.WorkerRepository;
import com.mySystem.patientekidney.services.interfaces.WorkerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class WorkerServiceImpl implements WorkerService {

    @Autowired
    private WorkerRepository workerRepository;
    public WorkerServiceImpl(WorkerRepository workerRepository){
        this.workerRepository = workerRepository;
    }

    @Override
    public List<Worker> findAllWorker() {
        return workerRepository.findAll();
    }

    @Override
    public Worker saveWorker(Worker worker) {
        return workerRepository.save(worker);
    }

    @Override
    public Optional<Worker> getWorkerById(Long idWorker) {
        return Optional.of(workerRepository.getById(idWorker));
    }

    @Override
    public void deleteWorkerById(Long idWorker) {
        workerRepository.deleteById(idWorker);
    }
}
