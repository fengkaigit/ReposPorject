package com.ey.dao.cmd;

import com.ey.dao.cmd.Command;

public interface CommandExecute {


  <T> T execute(Command<T> command);
}
