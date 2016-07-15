package com.smartling.connector.eloqua.sdk.rest.api;

//marker interface
public interface EloquaApi
{
    enum Depth
    {
        MINIMAL, PARTIAL, COMPLETE;

        @Override
        public String toString()
        {
            return name().toLowerCase();
        }
    }
}
