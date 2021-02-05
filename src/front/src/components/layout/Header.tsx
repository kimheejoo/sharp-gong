import React from 'react';
import styled from '@emotion/styled';

const Header: React.FC = () => {
  const isLogined = React.useMemo(() => false, []);
  return (
    <ul>
      {isLogined ? (
        <li>로그인아웃</li>
      ) : (
        <li>로그인</li>
      )}
      
    </ul>
  )
};


Header.displayName = 'Header';
export default React.memo(Header);