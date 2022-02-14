package com.example.cli;

import com.example.dao.RechargeDao;
import com.example.model.Borne;
import com.example.model.RechargeWaiting;
import com.example.service.RechargeService;
import org.beryx.textio.TextIO;
import org.beryx.textio.TextIoFactory;
import picocli.CommandLine.Command;

import javax.inject.Inject;

@Command(name = "greeting", mixinStandardHelpOptions = true)
public class Main implements Runnable{

    @Inject
    UserInterfaceCLI borneInterface;

    @Inject
    RechargeDao rechargeDao;

    @Inject
    RechargeService rechargeService;

    @Override
    public void run() {
        TextIO textIO = TextIoFactory.getTextIO();
        borneInterface.accept(textIO, new RunnerData(""));

        try {
            Borne b = borneInterface.getBorne();
            borneInterface.showBorneInfo(b);
            RechargeWaiting[] recharges;
            while(rechargeDao.hasNext()) {
                recharges = rechargeDao.getRechargesWaiting();
                int rechargeId = borneInterface.chooseRechargeToHandle(recharges);
                if(rechargeId == 0)
                    return;

                RechargeWaiting r = rechargeDao.getRecharge(rechargeId);
                this.rechargeService.handleRecharge(r, b);
                this.rechargeDao.removeRechargeFromQueue(r);
            }
        }
        catch(Exception e)
        {
           e.printStackTrace();
        }finally {
            borneInterface.exit();
        }
    }
}
