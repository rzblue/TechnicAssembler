/*
 * File name: MultiOutputStream.java
 * com.github.firecrafty.technicassembler.logging.MultiOutputStream
 *
 * Copyright (C) 2016 firecrafty
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

package com.github.firecrafty.technicassembler.logging;

import java.io.IOException;
import java.io.OutputStream;

/**
 * @author firecrafty
 */
public class MultiOutputStream extends OutputStream {
    OutputStream[] outs;

    MultiOutputStream(OutputStream... outs) {
        this.outs = outs;
    }

    @Override
    public void write(int b) throws IOException {
        for (OutputStream out : outs) {
            out.write(b);
        }
    }

    @Override
    public void write(byte b[]) throws IOException {
        for (OutputStream out : outs) {
            out.write(b);
        }
    }

    @Override
    public void write(byte[] b, int off, int len) throws IOException {
        for (OutputStream out : outs) {
            out.write(b, off, len);
        }
    }

    @Override
    public void flush() throws IOException {
        for (OutputStream out : outs) {
            out.flush();
        }
    }

    @Override
    public void close() throws IOException {
        for (OutputStream out : outs) {
            out.close();
        }
    }
}
